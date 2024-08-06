# think-oms
基于DDD四层架构的订单中台，此文只是一篇DEMO 供领域驱动设计实践参考

# 背景
设计一款可以同时支持接入自有平台，拼多多，抖音，淘宝天猫、小米、小红书、快手等平台的订单，做统一管理用于下游结算，履约，售后，安装等服务

![企业微信截图_17229378026391](https://github.com/user-attachments/assets/121efaf8-bbd4-4919-9403-76f6a66adbde)

# 战略设计实践

#### 产品愿景图

![企业微信截图_17224192429493](https://github.com/user-attachments/assets/f1e636e8-df7d-4c48-98b9-d1057893f985)



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

#### 上下文关系

![企业微信截图_17222331343001](https://github.com/user-attachments/assets/0422a0d9-9419-49a6-8b42-56b6652fa677)


# 战术设计实践

## 创建订单代码模型图
![diagram-14049767362457485694](https://github.com/user-attachments/assets/501036d6-2df1-47a9-a88c-b0058b98fc40)

### DDD分层代码调用时序图

![diagram-5973189397319537185](https://github.com/user-attachments/assets/22f72db8-a858-4648-a3b3-0e34e7fbaedb)



## 代码分层架构实践

#### 网上几种架构实践 核心是领域模型 外层依赖内层
![企业微信截图_20240724171510](https://github.com/user-attachments/assets/71554155-8446-4160-aa9d-1ea216eeb578)


#### 订单中台架构实战 四层架构

![企业微信截图_17224090641342](https://github.com/user-attachments/assets/654d58a8-6e42-4ce4-8dfd-6a024d4fbc18)



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
│     │  │              └─ service/ ............................... AppService (对应user case)
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
      │  │           └─ ohs/ ...................................... 
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
### 聚合

* 限制外界对聚合的对象访问
* 维护领域概念性完整
* 保证聚合内数据强一致性
* 变更状态：业务角度去表达领域逻辑
* 自给自足：操作自身属性进行业务逻辑
* 互为协作：与别的对象进行协作
```Java
/**
 * 订单履约聚合
 */
@Getter
public class OrderFulfillAggregate {

    private OrderId orderId;

    private boolean callback;

    private Map<String,ShippingSkuItem> orderSkuItems;

    private ShippingCallbackRecord shippingCallbackRecord;

    public static OrderFulfillAggregate create(OrderFulfillCommand command){
        command.validate();
        OrderFulfillAggregate aggregate = new OrderFulfillAggregate();
        aggregate.orderId = new OrderId(command.getOrderNo());
        aggregate.orderSkuItems = Maps.newHashMap();
        return aggregate;
    }

    /**
     * 回传校验
     */
    public void check(){
        this.callback = false;
        if(CollectionUtils.isEmpty(orderSkuItems)){
            Assert.isTrue(false,String.format("根据orderNo=[%s]查询不到订单信息!!!",this.orderId.getOrderNo()));
        }
        if(OrderSource.DOU_YIN.getCode() == this.orderId.getOrderSource().getCode()){
            //抖音平台无需发货回传
            return;
        }
        this.orderSkuItems.forEach((k,v)->{
            if(v.getSkuAmount() != 0){
                this.callback = true;
            }
        });
    }

    /**
     * 初始sku信息
     * @param skuItemInfos
     */
    public void initBaseInfo(List<SkuItemInfo> skuItemInfos){
        if(CollectionUtils.isEmpty(skuItemInfos)){
            return;
        }
        skuItemInfos.forEach(skuItemInfo -> {
            //SkuItemInfo 转 ShippingSkuItem
            orderSkuItems.put(skuItemInfo.getSkuFullInfo().getSkuId(),
                    new ShippingSkuItem(skuItemInfo.getSkuFullInfo().getSkuId(),skuItemInfo.getSkuFullInfo().getExternalSkuId(),
                    skuItemInfo.getSkuAmount()));
        });
    }

    /**
     * 更新发货数量
     * @param skuId
     * @param shippingAmount
     */
    public void modifyShippingAmount(String skuId,Integer shippingAmount){
        ShippingSkuItem shippingSkuItem  = orderSkuItems.get(skuId);
        Assert.notNull(shippingSkuItem,String.format("ofc skuId=%s 无法匹配订单skuId!!!",skuId));
        shippingSkuItem.modifyShippingAmount(shippingAmount);
    }

    /**
     * 创建发货回传记录
     */
    public void makeShippingCallbackRecord(Integer status,String callResult){
        this.shippingCallbackRecord = new ShippingCallbackRecord(this.orderId.getOrderNo(),this.orderId.getExternalOrderNo(),
                this.orderId.getOrderSource(), JSONObject.toJSONString(this.orderSkuItems),status,callResult);
    }
}
```
### 值对象
* 自我验证
* 自我组合
* 自我运算
* 属性在整个过程中,除构建方法外不允许改变


```Java
@Getter
public class StoreInfo {

    private String storeCode;
    private String storeName;

    public void init(String storeCode,String storeName){
        Assert.isNull(storeCode,"storeCode is null!!!");
        Assert.isNull(storeName,"storeName is null!!!");
        this.storeCode = storeCode;
        this.storeName = storeName;
    }

    public StoreInfo(String storeCode){
        Assert.isNull(storeCode,"storeCode is null!!!");
        this.storeCode = storeCode;
    }
```
### DP对象

本身也是值对象，附加了领域行为
```Java
@Getter
public class OrderId {
    private String orderNo;
    private  String externalOrderNo;
    private OrderSource orderSource;

    public OrderId(String externalOrderNo,OrderSource orderSource){
        this.externalOrderNo = externalOrderNo;
        this.orderSource = orderSource;
        this.orderNo = System.currentTimeMillis()+"";
    }
    
    public OrderId(String orderNo){
        this.orderNo = orderNo;
    }
}
```
### 业务方法和领域方法为区分

业务方法:未来一段时间可能发生变化的逻辑(低耦合，可以随时编排)，编排骤可以分开
```Java
 public void createOrder(OrderCreateCommand command){
        OrderCreateAggregate aggregate = OrderCreateAggregate.create(command);
        orderCreateDomainService.isExist(aggregate);
        orderCreateDomainService.initBaseInfo(aggregate);
        aggregate.check();
        aggregate.priceCalculate();
        orderCreateDomainService.deductInventory(aggregate);
        orderRepository.save(aggregate);
        orderEventPublisher.publish(new OrderCreatedEvent(aggregate.getOrderId().getOrderNo()));
    }
```

领域方法：未来一段时间逻辑不变(高内聚，长时间沉淀后的领域逻辑，场景不相关的，可复用的)，编排骤无法分开
```Java
orderCreateDomainService.initBaseInfo(aggregate);

    /**
     * 领域方法(高内聚低耦合)
     * @param aggregate
     */
    public void initBaseInfo(OrderCreateAggregate aggregate){
        this.initStoreInfo(aggregate);
        this.initSkuInfo(aggregate);
        this.initInvoiceInfo(aggregate);
        this.initShippingAddress(aggregate);
        this.initBuyer(aggregate);
    }
```

### 领域方法和领域行为区分
领域行为：聚合/实体内操作自身数据
```Java
aggregate.priceCalculate();

/**
* sku金额拆分计算(领域行为)
*/
    public void  priceCalculate(){
        //优惠金额，运费 等附加费用 根据 sku下单金额权重比例进行金额拆分
        this.skuItems = Lists.newArrayList();
        this.skuInfos.forEach(skuInfo -> {
            //拆组合商品 计算子sku需要均摊的金额，附加费用
            Map<FeeType,Long> feeAmountInfos = Maps.newHashMap();
            //feeAmountInfos.put(FeeType.TRAN_FEE,)
            //feeAmountInfos.put(FeeType.DISCOUNT_FEE,)
            OrderSkuItem skuItem = new OrderSkuItem(skuInfo.getSkuInfo().getSkuId(),skuInfo.getSkuInfo().getSkuCode(),
                    skuInfo.getSkuBuyAmount(),skuInfo.getSkuPayPrice(),feeAmountInfos);
            this.skuItems.add(skuItem);
        });
    }
```

领域方法:与南向网关合作数据,设计模式满足扩展需求,资源库/抽象网关提供数据,名称中需要包含动词
```Java
 orderCreateDomainService.deductInventory(aggregate);
 /**
     * 扣减库存 (领域方法)
     * 多领域协作
     * @param aggregate
     */
    public void deductInventory(OrderCreateAggregate aggregate){
        DeductInventoryRequest request = DeductInventoryRequest.builder()
                .build();
        DeductInventoryResponse response = inventoryGateway.deduct(request);
        aggregate.deductInventory(response.getInventoryInfos());
    }
```

### DDD和性能之间取舍和平衡

接入订单聚合是设计成批量接入还是单次接入？如何取舍

### DDD和数据一致性取舍和平衡

订单聚合持久化后，需要推送下游 结算、发票、发货等，是否在 订单流程编排中编排？调用外部服务超时如何处理

通过领域事件解耦,可以做一致性数据补偿
```Java
    //订单创建成功发布领域事件
    orderEventPublisher.publish(new OrderCreatedEvent(aggregate.getOrderId().getOrderNo()));

    //监听领域事件
    @EventListener(value = OrderCreatedEvent.class)
    @Async
    public void onApplicationEvent(OrderCreatedEvent event) {
        log.info("收到OrderCreatedEvent :orderNo=[{}]",event.getOrderNo());
        orderAppService.doHandleAfterOrderBeCreated(event.getOrderNo());
    }

     /**
     * 订单创建后续逻辑(可做数据一致性补偿)
     * 
     * @param orderNo
     */
    public void doHandleAfterOrderBeCreated(String orderNo){
        OrderQueryRequest request = OrderQueryRequest
                .builder()
                .orderNo(orderNo)
                .build();
        List<OrderInfo> orders = orderInfoGateway.query(request).getOrders();
        if(CollectionUtils.isEmpty(orders)){
            return;
        }
        //通知订单履约
        ofcGateway.fulfill(orderNo);
        //通知开发票
        invoiceGateway.issue(orderNo);
        //通知olap服务
        //通知结算
    }
```
