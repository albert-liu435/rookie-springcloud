cloudBus

## 概述

### 是什么

Spring Cloud Bus 配合 Spring Cloud Config 使用可以实现配置的动态刷新。Spring Cloud Bus是用来将分布式系统的节点与轻量级消息系统链接起来的框架，
它整合了Java的事件处理机制和消息中间件的功能。
Spring Clud Bus目前支持RabbitMQ和Kafka。

![img](.\pic\img.png)



### 能干嘛

Spring Cloud Bus能管理和传播分布式系统间的消息，就像一个分布式执行器，可用于广播状态更改、事件推送等，也可以当作微服务间的通信通道。

## SpringcloudBus动态刷新全局广播

### 设计思想

1、利用消息总线触发一个客户端/bus/refresh，而刷新所有的客户端的配置

![img_3](.\pic\img_3.png)

2、利用消息总线触发一个服务端ConfigServer的/bus/refresh端点，而刷新所有客户端的配置

![img_2](.\pic\img_2.png)

图二架构更适合

图一架构不适合原因如下：

打破了微服务的职责的单一性，因为微服务本身是业务模块，它本不应该承担刷新职责

破坏了微服务各节点的对等性

有一定的局限行，例如：微服务在迁移时，它的网络地址经常会发生编号，此时如果想要做自动刷新，就会增加更多的修

## 创建项目rookie-springcloud-bus

运行项目，然后修改springcloud-config里面的config-dev.yml中的版本号，然后访问http://localhost:3344/config-dev.yml和http://localhost:3355/configInfo，进行查看版本号是否变化，然后通过cmd命令行窗口执行如下命令

```java
curl -X POST "http://localhost:3344/actuator/busrefresh"
```

再次访问上面的连接，查看版本号的变化

### 刷新定点通知

公式：http://localhost:配置中心的端口号/actuator/busrefresh/{destination}

如下命令只是通知3355微服务

```java
curl -X POST "http://localhost:3344/actuator/busrefresh/rookie-config-order-consumer:3355"
```



通知总结

![img_4](.\pic\img_4.png)



[参考地址](https://docs.spring.io/spring-cloud-bus/docs/current/reference/html/#bus-refresh-endpoint)