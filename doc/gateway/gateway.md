gateway

[参考](https://docs.spring.io/spring-cloud/docs/current/reference/html/)

Gateway是在Spring生态系统之上构建的API网关服务，基于Spring 5，Spring Boot 2和 Project Reactor等技术。Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器功能， 例如：熔断、限流、重试等SpringCloud Gateway 是 Spring Cloud 的一个全新项目，基于 Spring 5.0+Spring Boot 2.0 和 Project Reactor 等技术开发的网关，它旨在为微服务架构提供一种简单有效的统一的 API 路由管理方式。 SpringCloud Gateway 作为 Spring Cloud 生态系统中的网关，目标是替代 Zuul，在Spring Cloud 2.0以上版本中，没有对新版本的Zuul 2.0以上最新高性能版本进行集成，仍然还是使用的Zuul 1.x非Reactor模式的老版本。而为了提升网关的性能，SpringCloud Gateway是基于WebFlux框架实现的，而WebFlux框架底层则使用了高性能的Reactor模式通信框架Netty。 Spring Cloud Gateway的目标提供统一的路由方式且基于 Filter 链的方式提供了网关基本的功能，例如：安全，监控/指标，和限流。      

SpringCloud Gateway 使用的Webflux中的reactor-netty响应式编程组件，底层使用了Netty通讯框架。

## GateWay作用

反向代理

鉴权

流量控制

熔断

日志监控

## 为什么选择GateWay

 Spring Cloud Gateway 具有如下特性： 基于Spring Framework 5, Project Reactor 和 Spring Boot 2.0 进行构建；动态路由：能够匹配任何请求属性；可以对路由指定 Predicate（断言）和 Filter（过滤器）；集成Hystrix的断路器功能；集成 Spring Cloud 服务发现功能；易于编写的 Predicate（断言）和 Filter（过滤器）；请求限流功能；支持路径重写。 

## GateWay三大核心

### 路由(Route)

路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由

### 断言(Predicate)

参考的是Java8的java.util.function.Predicate
开发人员可以匹配HTTP请求中的所有内容(例如请求头或请求参数)，如果请求与断言相匹配则进行路由

### 过滤(Filter)

指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或者之后对请求进行修改。

### 总体

 web请求，通过一些匹配条件，定位到真正的服务节点。并在这个转发过程的前后，进行一些精细化控制。predicate就是我们的匹配条件；而filter，就可以理解为一个无所不能的拦截器。有了这两个元素，再加上目标uri，就可以实现一个具体的路由了

## GateWay工作流程 

 客户端向 Spring Cloud Gateway 发出请求。然后在 Gateway Handler Mapping 中找到与请求相匹配的路由，将其发送到 Gateway Web Handler。 Handler 再通过指定的过滤器链来将请求发送到我们实际的服务执行业务逻辑，然后返回。过滤器之间用虚线分开是因为过滤器可能会在发送代理请求之前（“pre”）或之后（“post”）执行业务逻辑。 Filter在“pre”类型的过滤器可以做参数校验、权限校验、流量监控、日志输出、协议转换等，在“post”类型的过滤器中可以做响应内容、响应头的修改，日志的输出，流量监控等有着非常重要的作用。

路由转发+执行过滤器链

##  入门配置

新建rookie-springcloud-gateway项目

rookie-gateway-gateway项目中的yml配置如下

```yaml
server:
  port: 9527 #指定运行端口
spring:
  application:
    name: gateway-server #指定服务名称
  cloud:
    gateway:
      #discovery:
      #  locator:
      #    enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: http://localhost:8001          #匹配后提供服务的路由地址
          #uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**         # 断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: http://localhost:8001          #匹配后提供服务的路由地址
          #uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/lb/**         # 断言，路径相匹配的进行路由
            #- After=2020-02-21T15:51:37.485+08:00[Asia/Shanghai]
            #- Cookie=username,zzyy
            #- Header=X-Request-Id, \d+  # 请求头要有X-Request-Id属性并且值为整数的正则表达式

eureka:
  instance:
    hostname: gateway-server
  #server:
  #  enable-self-preservation: true
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

然后启动rookie-gateway-server,rookie-gateway-payment-provider,rookie-gateway-gateway项目，然后分别访问http://localhost:9527/payment/get/1和http://localhost:9527/payment/lb测试即可

### gateway网关路由的两种配置方式

上面是一种方式，另外一种方式如下，即采用编码的方式进行处理，如

```java
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_atguigu",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();

        return routes.build();
    }

}
```

然后访问http://localhost:9527/guonei即可访问http://news.baidu.com/guonei的信息

## 微服务名实现动态路由

配置文件如下

```yaml
server:
  port: 9527 #指定运行端口
spring:
  application:
    name: gateway-server #指定服务名称
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001          #匹配后提供服务的路由地址
          uri: lb://rookie-eureka-payment #匹配后提供服务的路由地址 lb表示启用Gateway的负载均衡功能
          predicates:
            - Path=/payment/get/**         # 断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001          #匹配后提供服务的路由地址
          uri: lb://rookie-eureka-payment #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/lb/**         # 断言，路径相匹配的进行路由
            #- After=2020-02-21T15:51:37.485+08:00[Asia/Shanghai]
            #- Cookie=username,zzyy
            #- Header=X-Request-Id, \d+  # 请求头要有X-Request-Id属性并且值为整数的正则表达式

eureka:
  instance:
    hostname: gateway-server
  #server:
  #  enable-self-preservation: true
  client:
    #表示是否将自己注册进EurekaServer默认为true。
    register-with-eureka: true
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetchRegistry: true
    service-url:
      #单机版
      defaultZone: http://eureka-server.dev.com:8761/eureka
```

然后启动rookie-gateway-server,rookie-gateway-payment-provider,rookie-gateway-gateway项目，然后分别访问http://localhost:9527/payment/get/1和http://localhost:9527/payment/lb测试即可

## Predicate的使用

[参考](https://docs.spring.io/spring-cloud-gateway/docs/3.1.1/reference/html/#the-after-route-predicate-factory)

## Filter的使用

作用

全局日志记录，统一网关鉴权

