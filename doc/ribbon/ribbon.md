ribbon

ribbon已经进入维护模式

Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端    负载均衡的工具。 简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法和服务调用。Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等。简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。我们很容易使用Ribbon实现自定义的负载均衡算法。   

[参考文档](https://github.com/Netflix/ribbon/wiki)

## 负载均衡(LB Load Balance)

### 集中式负载均衡(LB)

集中式LB即在服务的消费方和提供方之间使用独立的LB设施(可以是硬件，如F5, 也可以是软件，如nginx), 由该设施负责把访问请求通过某种策略转发至服务的提供方；

### 进程内负载均衡(LB)

 进程内LB 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。 Ribbon就属于进程内LB，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。  

## ribbon负载均衡

pom依赖中已经引入了ribbon,所以不需要额外

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```

### 启动集群

按照eureka步骤，分别启动rookie-ribbon-eureka-server集群和rookie-ribbon-payment-provider集群

```java
java -jar -Dspring.profiles.active=8761 target/rookie-ribbon-eureka-server-1.0.0.jar
java -jar -Dspring.profiles.active=8762 target/rookie-ribbon-eureka-server-1.0.0.jar
java -jar -Dspring.profiles.active=8763 target/rookie-ribbon-eureka-server-1.0.0.jar
```

```java
java -jar -Dspring.profiles.active=8001 target/rookie-ribbon-payment-provider-1.0.0.jar
java -jar -Dspring.profiles.active=8002 target/rookie-ribbon-payment-provider-1.0.0.jar
java -jar -Dspring.profiles.active=8003 target/rookie-ribbon-payment-provider-1.0.0.jar
```

