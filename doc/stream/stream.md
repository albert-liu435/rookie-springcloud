Stream

## 是什么

屏蔽底层消息中间件的差异,降低切换成本，统一消息的编程模型


什么是SpringCloudStream
官方定义 Spring Cloud Stream 是一个构建消息驱动微服务的框架。

应用程序通过 inputs 或者 outputs 来与 Spring Cloud Stream中binder对象交互。
通过我们配置来binding(绑定) ，而 Spring Cloud Stream 的 binder对象负责与消息中间件交互。
所以，我们只需要搞清楚如何与 Spring Cloud Stream 交互就可以方便使用消息驱动的方式。

通过使用Spring Integration来连接消息代理中间件以实现消息事件驱动。
Spring Cloud Stream 为一些供应商的消息中间件产品提供了个性化的自动化配置实现，引用了发布-订阅、消费组、分区的三个核心概念。

目前仅支持RabbitMQ、Kafka。

## 设计思想

### 标准MQ

![img](.\pic\img.png)

Messsage:生产者/消费者之间靠消息媒介传递消息内容

MessageChannel:消息通道，消息必须走特定的通道

消息通道里的消息如何被消费呢，谁负责收发处理

消息通道MessageChannel的子接口SubscribableChannel，由MessageHandler消息处理器所订阅

### 为什么用Cloud Stream

比方说我们用到了RabbitMQ和Kafka，由于这两个消息中间件的架构上的不同，
像RabbitMQ有exchange，kafka有Topic和Partitions分区，这些中间件的差异性导致我们实际项目开发给我们造成了一定的困扰，我们如果用了两个消息队列的其中一种，后面的业务需求，我想往另外一种消息队列进行迁移，这时候无疑就是一个灾难性的，一大堆东西都要重新推倒重新做，因为它跟我们的系统耦合了，这时候springcloud Stream给我们提供了一种解耦合的方式。

![img_1](.\pic\img_1.png)

#### stream凭什么可以统一底层差异

在没有绑定器这个概念的情况下，我们的SpringBoot应用要直接与消息中间件进行信息交互的时候，
由于各消息中间件构建的初衷不同，它们的实现细节上会有较大的差异性
通过定义绑定器作为中间层，完美地实现了应用程序与消息中间件细节之间的隔离。
通过向应用程序暴露统一的Channel通道，使得应用程序不需要再考虑各种不同的消息中间件实现。

通过定义绑定器Binder作为中间层，实现了应用程序与消息中间件细节之间的隔离。

#### Binder

在没有绑定器这个概念的情况下，我们的SpringBoot应用要直接与消息中间件进行信息交互的时候，由于各消息中间件构建的初衷不同，它们的实现细节上会有较大的差异性，通过定义绑定器作为中间层，完美地实现了应用程序与消息中间件细节之间的隔离。Stream对消息中间件的进一步封装，可以做到代码层面对中间件的无感知，甚至于动态的切换中间件(rabbitmq切换为kafka)，使得微服务开发的高度解耦，服务可以关注更多自己的业务流程

![img_2](.\pic\img_2.png)

通过定义绑定器Binder作为中间层，实现了应用程序与消息中间件细节之间的隔离。

Binder可以生成Binding，Binding用来绑定消息容器的生产者和消费者，它有两种类型，INPUT和OUTPUT，INPUT对应于消费者，OUTPUT对应于生产者。

#### Stream中的消息通信方式遵循了发布-订阅模式

Topic主题进行广播

## Spring Cloud Stream标准流程套路

![img_3](.\pic\img_3.png)

![img_4](.\pic\img_4.png)

### Binder

很方便的连接中间件，屏蔽差异

### Channel

通道，是队列Queue的一种抽象，在消息通讯系统中就是实现存储和转发的媒介，通过Channel对队列进行配置通道，是队列Queue的一种抽象，在消息通讯系统中就是实现存储和转发的媒介，通过Channel对队列进行配置

### Source和Sink

简单的可理解为参照对象是Spring Cloud Stream自身，
从Stream发布消息就是输出，接受消息就是输入。

## 常用注解

![img_5](.\pic\img_5.png)

## 消息生产者

rookie-stream-payment-provider

配置文件如下

```yaml
server:
  port: 8001
spring:
  application:
    name: rookie-eureka-payment
  cloud:
    stream:
      binders: # 在此处配置要绑定的rabbitmq的服务信息；
        defaultRabbit: # 表示定义的名称，用于于binding整合
          type: rabbit # 消息组件类型
          environment: # 设置rabbitmq的相关的环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        output: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的Exchange名称定义
          content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
          binder: defaultRabbit # 设置要绑定的消息服务的具体设置



#spring:
#  application:
#    name: cloud-stream-provider
#  cloud:
#    stream:
#      binders: # 在此处配置要绑定的rabbitmq的服务信息；
#        defaultRabbit: # 表示定义的名称，用于于binding整合
#          type: rabbit # 消息组件类型
#          environment: # 设置rabbitmq的相关的环境配置
#            spring:
#              rabbitmq:
#                host: localhost
#                port: 5672
#                username: guest
#                password: guest
#      bindings: # 服务的整合处理
#        output: # 这个名字是一个通道的名称
#          destination: studyExchange # 表示要使用的Exchange名称定义
#          content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
#          binder: defaultRabbit # 设置要绑定的消息服务的具体设置


eureka:
  instance:
    instance-id: rookie-payment-provider
  #  instance:
#    prefer-ip-address: true
  client:
    #表示是否将自己注册进EurekaServer默认为true。
    register-with-eureka: true
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetchRegistry: true
    service-url:
      #单机版
      defaultZone: http://eureka-server.dev.com:8761/eureka
      #defaultZone: ${EUREKA_DEFAULT_ZONE:http://eureka-server.dev.com:8761/eureka}
      # 集群版
      #defaultZone: http://eureka.server8761.com:8761/eureka/,http://eureka.server8762.com:8762/eureka/,http://eureka.server8763.com:8763/eureka/
```

启动该项目并调用http://localhost:8001/sendMessage进行消息发送

## 消息消费者

配置文件如下

```yaml
server:
  port: 80
spring:
  application:
    name: rookie-eureka-order-consumer


  cloud:
    stream:
      binders: # 在此处配置要绑定的rabbitmq的服务信息；
        defaultRabbit: # 表示定义的名称，用于于binding整合
          type: rabbit # 消息组件类型
          environment: # 设置rabbitmq的相关的环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        input: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的Exchange名称定义
          content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
          binder: defaultRabbit # 设置要绑定的消息服务的具体设置

eureka:
  instance:
    instance-id: rookie-order-consumer
  #  instance:
#    prefer-ip-address: true
  client:
    #表示是否将自己注册进EurekaServer默认为true。
    register-with-eureka: true
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetchRegistry: true
    service-url:
      #单机版
      defaultZone: http://eureka-server.dev.com:8761/eureka
      #defaultZone: ${EUREKA_DEFAULT_ZONE:http://eureka-server.dev.com:8761/eureka}
      # 集群版
      #defaultZone: http://eureka.server8761.com:8761/eureka/,http://eureka.server8762.com:8762/eureka/,http://eureka.server8763.com:8763/eureka/
```

启动，然后向生产者发送消息，即可看到消费者接受到消息了

### 消费者问题

#### 重复消费问题

如果启动两个消费者，则会出现消费重复的问题。

原理：微服务应用放置于同一个group中，就能够保证消息只会被其中一个应用消费一次。不同的组是可以消费的，同一个组内会发生竞争关系，只有其中一个可以消费。

在配置文件中添加group配置即可，如果group的值相同，则不进行重复消费

#### 持久化问题

有分组后默认消息就会持久化

#### 分组

比如在如下场景中，订单系统我们做集群部署，都会从RabbitMQ中获取订单信息，
那如果一个订单同时被两个服务获取到，那么就会造成数据错误，我们得避免这种情况。
这时我们就可以使用Stream中的消息分组来解决

![img_6](.\pic\img_6.png)

注意在Stream中处于同一个group中的多个消费者是竞争关系，就能够保证消息只会被其中一个应用消费一次。
不同组是可以全面消费的(重复消费)，
同一组内会发生竞争关系，只有其中一个可以消费。

