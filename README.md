# think-oms
基于DDD四层架构的订单中台，此文只是一篇DEMO 供领域驱动设计实践参考

# 背景
设计一款可以同时支持接入自有平台，拼多多，抖音，淘宝天猫、小米、小红书、快手等平台的订单，做统一管理用于下游结算，履约，售后，安装等服务

# 战略设计实践

#### 用例图：

##### 创建订单

![diagram-5860164288651623821](https://github.com/user-attachments/assets/9fd98c3a-cae9-446c-ace1-6c6b7e0b92aa)

##### 订单履约(订单发货)

![diagram-14395309108625627799](https://github.com/user-attachments/assets/4d478c25-ce90-42b0-a708-241f5e091dbe)

##### 订单查询
![diagram-15589063498785607716](https://github.com/user-attachments/assets/563359ef-914e-4c73-af21-8066a3826a12)


##### 订单更新
暂未画

#### 领域模型

![企业微信截图_1721810362293](https://github.com/user-attachments/assets/65722a4a-493a-4bec-bf46-bd7384da73ed)

#### 领域划分

![企业微信截图_1721878884495](https://github.com/user-attachments/assets/dde20ab9-5e06-43e1-84a8-c1bc15ed66ea)




# 战术设计实践

## 创建订单代码模型图
![diagram-14049767362457485694](https://github.com/user-attachments/assets/501036d6-2df1-47a9-a88c-b0058b98fc40)


##  代码落地
### 贫血模型(属性和行为分离)
```Java
//所有类属性对外暴露和修改
@Data
public class OrderInfo {

    private String orderNo;

    private String externalOrderNo;

    private OrderSource orderSource;

    private OrderStatus orderStatus;

    public List<SkuItemInfo> skuInfos;

}

OrderInfo orderInfo = new OrderInfo();
//发生业务 需要挂起订单 ,已发货的无需挂起
if(this.orderStatus != OrderStatus.FULFILLED){
    orderInfo.setOrderStatus(OrderStatus.HANG_UP);//类的属性随时可以通过set方法变更
}

```
### 充血模型(封装属性和领域行为)
```Java
//只暴露订单域和其他域需要协助的类属性
@Getter
public class OrderInfo {

    private String orderNo;

    private String externalOrderNo;

    private OrderSource orderSource;

    private OrderStatus orderStatus;

    public List<SkuItemInfo> skuInfos;

    public static OrderInfo create(OrderCreateCommand createCommand){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.externalOrderNo = createCommand.getExternalOrderNo();
        orderInfo.orderSource = createCommand.getOrderSource();
        //其他属性赋值
        return orderInfo;
    }
    /**
     * 订单挂起(领域方法)
     */
    public void hangup(){
        if(this.orderStatus == OrderStatus.FULFILLED){
            return;
        }
        this.orderStatus = OrderStatus.HANG_UP;
    }
}
OrderCreateCommand command = getOrderCreateCommand();
OrderInfo orderInfo = OrderInfo.create(command);
//发生业务 需要挂起订单 已发货的无需挂起
orderInfo.hangup();

```

### 流程编排(低耦合)
```Java
 //创建订单聚合
 OrderCreateAggregate aggregate = OrderCreateAggregate.create(command);
 //检验订单是否存在
 orderCreateDomainService.isExist(aggregate);
 //通过domian service 与外部商品域、用户域、基本信息域完成协作(高内聚)
 orderCreateDomainService.initBaseInfo(aggregate);
 //订单接入检查,调用聚合的领域方法
 aggregate.check();
 //订单金额拆分,调用聚合的领域方法
 aggregate.priceCalculate();
 //通过domian service 与库存域 完成库存扣减
 orderCreateDomainService.deductInventory(aggregate);
 //持久化聚合
 orderRepository.save(aggregate);
 //发布领域事件，通知订单创建成功
 orderEventPublisher.publish(new OrderCreatedEvent(aggregate.getOrderId().getOrderNo()));
```
### 领域服务(与外部协作领域行为，不适合放在聚合)
```Java
orderCreateDomainService.deductInventory(aggregate);

public void deductInventory(OrderCreateAggregate aggregate){
    DeductInventoryRequest request = DeductInventoryRequest.builder()
                .build();
    //通过南向网关调用 库存域进行库存扣减
    DeductInventoryResponse response = inventoryGateway.deduct(request);
    //domain service 调用聚合的领域方法 完成库存扣减后逻辑
    aggregate.deductInventory(response.getInventoryInfos());
}
    
```
### SOLID原则


### 业务行为和领域行为区分

业务行为:未来一段时间可能发生变化的逻辑(低耦合，可以随时编排)

领域行为：未来一段时间逻辑不变(高内聚，长时间沉淀后的领域逻辑)


### DDD和性能之间取舍和平衡


### DDD和数据一致性取舍和平衡


【创建订单】DDD分层代码调用时序图
![diagram-10159225477760837334](https://github.com/user-attachments/assets/08e6a62d-dcd2-46e1-9b25-153fc053d672)


## 代码分层架构实践

#### 网上几种架构实践 核心是领域模型 外层依赖内层
![企业微信截图_20240724171510](https://github.com/user-attachments/assets/71554155-8446-4160-aa9d-1ea216eeb578)



#### 订单中台架构实战 四层架构
![image](https://github.com/user-attachments/assets/51593795-74c6-4c3d-8dd0-1edf68975115)


### 代码结构说明
<pre>
think-oms/
├─ think-oms-application/ ......................................... 应用层
│  └─ src/ ........................................................ 
│     ├─ main/ .................................................... 
│     │  ├─ java/ ................................................. 
│     │  │  └─ com/ ............................................... 
│     │  │     └─ think/ .......................................... 
│     │  │        └─ oms/ ......................................... 
│     │  │           └─ app/ ...................................... 
│     │  │              └─ service/ ............................... AppService (对应use case)
│     │  └─ resources/ ............................................ 
│     └─ test/ .................................................... 
│        └─ java/ ................................................. 
├─ think-oms-domain/ .............................................. 领域层
│  └─ src/ ........................................................ 
│     ├─ main/ .................................................... 
│     │  ├─ java/ ................................................. 
│     │  │  └─ com/ ............................................... 
│     │  │     └─ think/ .......................................... 
│     │  │        └─ oms/ ......................................... 
│     │  │           └─ domain/ ................................... 
│     │  │              ├─ model/ ................................. 
│     │  │              │  ├─ aggregate/ .......................... 聚合/聚合根
│     │  │              │  │  ├─ ass/ ............................. 
│     │  │              │  │  ├─ create/ .......................... 
│     │  │              │  │  └─ fulfill/ ......................... 
│     │  │              │  ├─ constant/ ........................... 业务枚举
│     │  │              │  ├─ dp/ ................................. DP模型
│     │  │              │  └─ valueobject/ ........................ 值对象
│     │  │              ├─ pl/ .................................... PL转换层
│     │  │              │  ├─ command/ ............................ 创建聚合根command
│     │  │              │  ├─ event/ .............................. 领域事件
│     │  │              │  ├─ query/ .............................. query
│     │  │              │  ├─ request/ ............................ 网关request
│     │  │              │  └─ response/ ........................... 网关response
│     │  │              ├─ port/ .................................. 领域层port(南向网关)
│     │  │              │  ├─ gateway/ ............................ 网关
│     │  │              │  ├─ publisher/ .......................... 领域事件发布
│     │  │              │  └─ repository/ ......................... 资源库
│     │  │              └─ service/ ............................... 领域Service
│     │  └─ resources/ ............................................ 
│     └─ test/ .................................................... 
│        └─ java/ ................................................. 
├─ think-oms-infrastructure/ ...................................... 基础设施层
│  └─ src/ ........................................................ 
│     ├─ main/ .................................................... 
│     │  ├─ java/ ................................................. 
│     │  │  └─ com/ ............................................... 
│     │  │     └─ think/ .......................................... 
│     │  │        └─ oms/ ......................................... 
│     │  │           └─ infrastructure/ ........................... 
│     │  │              ├─ acl/ ................................... 南向网关(port具体实现)
│     │  │              │  ├─ api/ ................................ 外部订单协议实现
│     │  │              │  │  ├─ douyin/ .......................... 
│     │  │              │  │  ├─ kuaishou/ ........................ 
│     │  │              │  │  ├─ mijia/ ........................... 
│     │  │              │  │  ├─ pdd/ ............................. 
│     │  │              │  │  └─ taobao/ .......................... 
│     │  │              │  ├─ gateway/ ............................ 对应port-gateway具体实现
│     │  │              │  ├─ pl/ ................................. PL工具
│     │  │              │  ├─ publisher/ .......................... 对应port-publisher具体实现
│     │  │              │  └─ repository/ ......................... 对应port-repository具体实现
│     │  │              ├─ common/ ................................ 通用
│     │  │              │  └─ util/ ............................... 工具类
│     │  │              └─ core/ .................................. 技术具体实现
│     │  │                 ├─ http/ ............................... http实现
│     │  │                 ├─ mybatis/ ............................ orm实现
│     │  │                 ├─ redis/ .............................. cache实现
│     │  │                 └─ rockermq/ ........................... mqs实现
│     └─ test/ .................................................... 
│        └─ java/ ................................................. 
└─ think-oms-interface/ ........................................... 接口层(北向网关)
   ├─ pom.xml ..................................................... 
   └─ src/ ........................................................ 
      ├─ main/ .................................................... 
      │  ├─ java/ ................................................. 
      │  │  └─ com/ ............................................... 
      │  │     └─ think/ .......................................... 
      │  │        └─ oms/ ......................................... 
      │  │           └─ osh/ ...................................... 
      │  │              ├─ controller/ ............................ http接口
      │  │              ├─ dto/ ................................... DTO
      │  │              ├─ job/ ................................... 定时任务
      │  │              ├─ listener/ .............................. 本地事件监听
      │  │              ├─ mq/ .................................... 远程事件监听
      │  │              └─ rpc/ ................................... RPC接口
      │  └─ resources/ ............................................ 
      └─ test/ .................................................... 
         └─ java/ ................................................. 
</pre>
