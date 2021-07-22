## seata-demo-tcc-parent项目

用来测试seata全局事务性,使用的TCC模式

## 项目目录

seata-test  
|__ seata-demo-tcc-account-provider # 账户服务  
|__ seta-demo-tcc-api # 所有服务的API接口    
|__ seta-demo-tcc-order-provider # 订单服务   
|__ seta-demo-tcc-storage-provider # 仓库存储服务  
|__ seta-demo-tcc-web # 业务层

需要自行部署seata服务，如何部署，请看这个文档，https://github.com/phper666/docker-compose-seata