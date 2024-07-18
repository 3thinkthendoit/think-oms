# think-oms
基于DDD四层架构的订单中台

# 代码分层架构实践
![image](https://github.com/user-attachments/assets/51593795-74c6-4c3d-8dd0-1edf68975115)


# 代码结构说明
<pre>
think-oms
├─ 📁think-oms-application
│  ├─ 📁src
│  │  └─ 📁main
│  │     └─ 📁java
│  │        └─ 📁com
│  │           └─ 📁think
│  │              └─ 📁oms
│  │                 └─ 📁app
│  │                    └─ 📁service
│  │                       └─ 📄OrderAppService.java
├─ 📁think-oms-domain
│  ├─ 📁src
│  │  └─ 📁main
│  │     └─ 📁java
│  │        └─ 📁com
│  │           └─ 📁think
│  │              └─ 📁oms
│  │                 └─ 📁domain
│  │                    ├─ 📁model
│  │                    │  ├─ 📁aggregate
│  │                    │  │  ├─ 📁create
│  │                    │  │  │  ├─ 📄OrderCreateAggregate.java
│  │                    │  │  │  ├─ 📄OrderInvoice.java
│  │                    │  │  │  ├─ 📄OrderSku.java
│  │                    │  │  │  └─ 📄OrderSkuItem.java
│  │                    │  │  └─ 📁fulfill
│  │                    │  │     ├─ 📄OrderFulfillAggregate.java
│  │                    │  │     ├─ 📄ShippingCallbackRecord.java
│  │                    │  │     └─ 📄ShippingSkuItem.java
│  │                    │  ├─ 📁constant
│  │                    │  │  ├─ 📄FeeType.java
│  │                    │  │  ├─ 📄OrderSkuStatus.java
│  │                    │  │  ├─ 📄OrderSource.java
│  │                    │  │  ├─ 📄OrderStatus.java
│  │                    │  │  ├─ 📄OrderType.java
│  │                    │  │  └─ 📄SkuType.java
│  │                    │  ├─ 📁dp
│  │                    │  │  └─ 📄OrderId.java
│  │                    │  └─ 📁valueobject
│  │                    │     ├─ 📄SkuInfo.java
│  │                    │     ├─ 📄StoreInfo.java
│  │                    │     └─ 📄UserInfo.java
│  │                    ├─ 📁pl
│  │                    │  ├─ 📁command
│  │                    │  │  ├─ 📄OrderAssCommand.java
│  │                    │  │  ├─ 📄OrderCreateCommand.java
│  │                    │  │  └─ 📄OrderFulfillCommand.java
│  │                    │  ├─ 📁event
│  │                    │  │  ├─ 📄OrderCreatedEvent.java
│  │                    │  │  ├─ 📄OrderOperationEvent.java
│  │                    │  │  └─ 📄OrderUpdatedEvent.java
│  │                    │  ├─ 📁query
│  │                    │  │  └─ 📄OrderInfoQuery.java
│  │                    │  ├─ 📁request
│  │                    │  │  ├─ 📄DeductInventoryRequest.java
│  │                    │  │  ├─ 📄OfcOrderQueryRequest.java
│  │                    │  │  ├─ 📄OrderQueryRequest.java
│  │                    │  │  ├─ 📄ShippingCallbackRequest.java
│  │                    │  │  └─ 📄SkuInfoQueryRequest.java
│  │                    │  ├─ 📁response
│  │                    │  │  ├─ 📄DeductInventoryResponse.java
│  │                    │  │  ├─ 📄OfcOrderQueryResponse.java
│  │                    │  │  ├─ 📄OrderQueryResponse.java
│  │                    │  │  ├─ 📄ShippingCallbackResponse.java
│  │                    │  │  └─ 📄SkuInfoQueryResponse.java
│  │                    │  ├─ 📄OrderInfo.java
│  │                    │  ├─ 📄OrderSkuInfo.java
│  │                    │  ├─ 📄SkuFullInfo.java
│  │                    │  └─ 📄SkuItemInfo.java
│  │                    ├─ 📁port
│  │                    │  ├─ 📁gateway
│  │                    │  │  ├─ 📄InventoryGateway.java
│  │                    │  │  ├─ 📄InvoiceGateway.java
│  │                    │  │  ├─ 📄OfcGateway.java
│  │                    │  │  ├─ 📄OrderInfoGateway.java
│  │                    │  │  ├─ 📄ShippingCallbackGateway.java
│  │                    │  │  └─ 📄SkuInfoQueryGateway.java
│  │                    │  ├─ 📁publisher
│  │                    │  │  └─ 📄OrderEventPublisher.java
│  │                    │  └─ 📁repository
│  │                    │     └─ 📄OrderRepository.java
│  │                    └─ 📁service
│  │                       ├─ 📄OrderCreateDomainService.java
│  │                       └─ 📄OrderFulfillDomainService.java
├─ 📁think-oms-infrastructure
│  ├─ 📁src
│  │  └─ 📁main
│  │     ├─ 📁java
│  │     │  └─ 📁com
│  │     │     └─ 📁think
│  │     │        └─ 📁oms
│  │     │           └─ 📁infrastructure
│  │     │              ├─ 📁acl
│  │     │              │  ├─ 📁api
│  │     │              │  │  ├─ 📁douyin
│  │     │              │  │  │  └─ 📄DouyinClient.java
│  │     │              │  │  ├─ 📁pdd
│  │     │              │  │  │  └─ 📄PinduoduoClient.java
│  │     │              │  │  └─ 📁taobao
│  │     │              │  │     └─ 📄TaoBaoClient.java
│  │     │              │  ├─ 📁gateway
│  │     │              │  │  ├─ 📄InventoryGatewayImpl.java
│  │     │              │  │  ├─ 📄InvoiceGatewayImpl.java
│  │     │              │  │  ├─ 📄OfcGatewayImpl.java
│  │     │              │  │  ├─ 📄OrderInfoGatewayImpl.java
│  │     │              │  │  ├─ 📄ShippingCallbackGatewayImpl.java
│  │     │              │  │  └─ 📄SkuInfoQueryGatewayImpl.java
│  │     │              │  ├─ 📁pl
│  │     │              │  │  ├─ 📄OrderPLUtil.java
│  │     │              │  │  └─ 📄SkuPLUtil.java
│  │     │              │  ├─ 📁publisher
│  │     │              │  │  └─ 📄OrderEventPublisherImpl.java
│  │     │              │  └─ 📁repository
│  │     │              │     └─ 📄OrderRepositoryImpl.java
│  │     │              └─ 📁core
│  │     │                 ├─ 📁http
│  │     │                 │  └─ 📄HttpClient.java
│  │     │                 ├─ 📁mybatis
│  │     │                 │  ├─ 📁mapper
│  │     │                 │  │  ├─ 📄OrderInfoMapper.java
│  │     │                 │  │  ├─ 📄OrderSkuInfoMapper.java
│  │     │                 │  │  └─ 📄OrderSkuItemInfoMapper.java
│  │     │                 │  └─ 📁po
│  │     │                 │     ├─ 📄OrderBaseInfo.java
│  │     │                 │     ├─ 📄OrderSkuInfo.java
│  │     │                 │     └─ 📄OrderSkuItemInfo.java
│  │     │                 ├─ 📁redis
│  │     │                 │  └─ 📄RedisClient.java
│  │     │                 └─ 📁rockermq
│  │     │                    └─ 📄RocketMqClient.java
│  │     └─ 📁resources
│  │        └─ 📁mapper
│  │           ├─ 📄OrderInfoMapper.xml
│  │           ├─ 📄OrderSkuInfoMapper.xml
│  │           └─ 📄OrderSkuItemInfoMapper.xml
├─ 📁think-oms-interface
│  ├─ 📁src
│  │  └─ 📁main
│  │     └─ 📁java
│  │        └─ 📁com
│  │           └─ 📁think
│  │              └─ 📁oms
│  │                 └─ 📁osh
│  │                    ├─ 📁controller
│  │                    │  └─ 📄MiJiaController.java 
│  │                    ├─ 📁dto
│  │                    │  ├─ 📁req
│  │                    │  │  └─ 📄OrderInfoQueryReq.java
│  │                    │  └─ 📁resp
│  │                    │     └─ 📄OrderInfoQueryResp.java
│  │                    ├─ 📁job
│  │                    │  ├─ 📄PinDuoDuoOrderTask.java
│  │                    │  └─ 📄TaoBaoOrderTask.java
│  │                    ├─ 📁listener
│  │                    │  ├─ 📄OrderCreatedListener.java
│  │                    │  └─ 📄OrderUpdatedListener.java
│  │                    ├─ 📁mq
│  │                    │  └─ 📄OfcConsumer.java
│  │                    ├─ 📁rpc
│  │                    │  ├─ 📁impl
│  │                    │  │  └─ 📄OrderInfoIfaceImpl.java
│  │                    │  └─ 📄OrderInfoIface.java
│  │                    └─ 📄ThinkOmsApplication.java
</pre>
