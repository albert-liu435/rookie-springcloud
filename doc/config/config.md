config

## 分布式面临的问题

微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务。由于每个服务都需要必要的配置信息才能运行，所以一套集中式的、动态的配置管理设施是必不可少的。SpringCloud提供了ConfigServer来解决这个问题，我们每一个微服务自己带着一个application.yml，上百个配置文件的管理

 是什么 SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。 怎么玩SpringCloud Config分为服务端和客户端两部分。 服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密/解密信息等访问接口 客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。 

## Spring-cloud-config

不同环境不同配置，动态化的配置更新，分环境部署比如dev/test/prod/beta/release

运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息

当配置发生变动时，服务不需要重启即可感知到配置的变化并应用新的配置

## 项目搭建

### 创建rookie-springcloud-config项目server

创建rookie-config-center项目，并配置文件如下

```java
server:
  port: 3344 #指定运行端口
spring:
  application:
    name: rookie-config-center #指定服务名称

  cloud:
    config:
      server:
        git:
          uri: https://github.com/albert-liu435/spring-cloud-config #GitHub上面的git仓库名字
          ####搜索目录
          search-paths:
            - spring-cloud-config
          username: 你的用户名
          password: 你的密码
      ####读取分支
      label: main

eureka:
  instance:
    hostname: eureka-server.dev.com #指定主机地址
  #server:
  #  enable-self-preservation: true
  client:
    fetch-registry: false #指定是否要从注册中心获取服务（注册中心不需要开启）
    register-with-eureka: false #指定是否要注册到注册中心（注册中心不需要开启）
    service-url:
      #设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址（单机）。
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

然后启动rookie-config-server和rookie-config-center项目，访问http://localhost:3344/main/config-dev.yml，http://localhost:3344/dev/config-dev.yml即可看到配置文件 中的信息。

### 配置读取规则

[参考官网](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)

#### 读取规则一

 /{label}/{application}-{profile}.yml,

如http://localhost:3344/main/config-dev.yml，http://localhost:3344/dev/config-dev.yml

#### 读取规则二

/{application}-{profile}.yml

http://localhost:3344/config-dev.yml，http://localhost:3344/config-dev.yml

#### 读取规则三

/{application}/{profile}/{label}

http://localhost:3344/config/dev/main，http://localhost:3344/config/dev/dev

### 创建rookie-springcloud-config项目client

rookie-config-order-consumer项目，配置文件如下

```yaml
server:
  port: 3355
spring:
  application:
    name: rookie-config-order-consumer
  config:
    import: "optional:configserver:http://localhost:3344"
  cloud:
    #Config客户端配置
    config:
      label: main #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称   上述3个综合：master分支上config-dev.yml的配置文件被读取http://localhost:3344/main/config-dev.yml
#      uri: http://localhost:3344 #配置中心地址k


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

启动之后访问http://localhost:3355/configInfo即可看到获取了配置文件

### 分布式配置动态刷新

rookie-config-order-consumer项目，配置文件如下

```yaml
server:
  port: 3355
spring:
  application:
    name: rookie-config-order-consumer
  #spring-cloud-config最新版本的建议配置
  config:
    import: "optional:configserver:http://localhost:3344"
  cloud:
    #Config客户端配置
    config:
      label: main #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称   上述3个综合：master分支上config-dev.yml的配置文件被读取http://localhost:3344/main/config-dev.yml
#      uri: http://localhost:3344 #配置中心地址k


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
      
# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

然后在windows命令行执行curl -X POST "http://localhost:3355/actuator/refresh

然后访问http://localhost:3355/configInfo即可看到配置信息已经变更