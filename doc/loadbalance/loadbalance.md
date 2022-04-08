spring-cloud-loadbalance

Spring cloud在2020版本之后舍弃了 ，改用自己的实现的loadbalance，详细参考

[参考文档](https://docs.spring.io/spring-cloud-commons/docs/3.1.1/reference/html/#switching-between-the-load-balancing-algorithms)

## 负载均衡(LB Load Balance)

### 集中式负载均衡(LB)

集中式LB即在服务的消费方和提供方之间使用独立的LB设施(可以是硬件，如F5, 也可以是软件，如nginx), 由该设施负责把访问请求通过某种策略转发至服务的提供方；

### 进程内负载均衡(LB)

 进程内LB 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。 Ribbon就属于进程内LB，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。  

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

