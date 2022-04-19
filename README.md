# spa-cloud

#### 介绍
基于spa框架的微服务系统，DEMO版本，目前实现了注册中心和feign远程调用。一个简单的RPC框架。

使用说明，分别下载demo  

注册中心 https://gitee.com/java_moon/spa-cloud-demo-eureka
服务1   https://gitee.com/java_moon/spa-cloud-demo-client1
服务2   https://gitee.com/java_moon/spa-cloud-demo-client2

先启动注册中心，然后分别启动服务1和服务2.调用服务1的接口即可本地调用服务2的方法。http://localhost:5555/api/getUser?name=dalao

注册中心控制台：http://localhost:3333/spa/getServices   可以查看已注册的服务列表，后期优化界面


