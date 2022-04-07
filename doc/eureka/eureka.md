eureka

### 服务治理和注册 

####  什么是服务治理

Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务治理       在传统的rpc远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，管理比较复杂，所以需要使用服务治理，管理服务于服务之间依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册。

#### 什么是服务注册

什么是服务注册与发现Eureka采用了CS的设计架构，Eureka Server 作为服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用 Eureka的客户端连接到 Eureka Server并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。在服务注册与发现中，有一个注册中心。当服务器启动的时候，会把当前自己服务器的信息 比如 服务地址通讯地址等以别名方式注册到注册中心上。另一方（消费者|服务提供者），以该别名的方式去注册中心上获取到实际的服务通讯地址，然后再实现本地RPC调用RPC远程调用框架核心设计思想：在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系(服务治理概念)。在任何rpc远程框架中，都会有一个注册中心(存放服务地址相关信息(接口地址))    

### eureka单机

#### Eureka组件

Eureka包含两个组件：Eureka Server和Eureka Client Eureka Server提供服务注册服务各个微服务节点通过配置启动后，会在EurekaServer中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到。 EurekaClient通过注册中心进行访问是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询(round-robin)负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳(默认周期为30秒)。如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，EurekaServer将会从服务注册表中把这个服务节点移除（默认90秒）

#### eureka server单机服务搭建

##### 1、首先在配置文件中 添加如下信息

```java
#eureka 服务配置
127.0.0.1 eureka-server.dev.com
```

##### 2、创建项目rookie-springcloud-eureka/rookie-eureka-server

详细配置查看rookie-springcloud-eureka/rookie-eureka-server项目。

##### 3、启动单实例rookie-eureka-server

首先将rookie-springcloud-eureka/rookie-eureka-server项目打包，打包后生成一个rookiie-eureka-server-11.0.0.jar文件，然后打开windows cmd命令行，找到该jar包所在的路径，并运行命令

```java
java -jar -Dspring.profiles.active=dev rookie-eureka-server-1.0.0.jar
```

通过访问http://eureka-server.dev.com:8761/查看启动是否成功

#### eureka client单机服务提供者

##### 1、创建项目rookie-springcloud-eureka/rookie-eureka-payment-provider

详细配置查看rookie-springcloud-eureka/rookie-eureka-payment-provider项目。

##### 2、启动单实例rookie-eureka-payment-provider

首先将rookie-springcloud-eureka/rookie-eureka-payment-provider项目打包，打包后生成一个rookie-eureka-payment-provider-1.0.0.jar文件，然后打开windows cmd命令行，找到该jar包所在的路径，并运行命令

```java
java -jar -Dspring.profiles.active=dev rookie-eureka-payment-provider-1.0.0.jar
```

#### eureka client单机服务消费者

##### 1、创建项目rookie-springcloud-eureka/rookie-eureka-order-consumer

详细配置查看rookie-springcloud-eureka/rookie-eureka-order-consumer项目。

##### 2、启动单实例rookie-eureka-order-consumer

首先将rookie-springcloud-eureka/rookie-eureka-order-consumer项目打包，打包后生成一个rookie-eureka-order-consumer-1.0.0.jar文件，然后打开windows cmd命令行，找到该jar包所在的路径，并运行命令

```java
java -jar -Dspring.profiles.active=dev rookie-eureka-order-consumer-1.0.0.jar
```

然后调用http://localhost/consumer/payment/get/1即可

### eureka集群

#### eureka server单机服务搭建

在host中配置如下ip映射

```java
127.0.0.1 eureka.server8761.com
127.0.0.1 eureka.server8762.com
127.0.0.1 eureka.server8763.com
```

分别打开三个命令行窗口，分别运行下面三个命令

```java
java -jar -Dspring.profiles.active=8761 rookie-eureka-server-1.0.0.jar
java -jar -Dspring.profiles.active=8762 rookie-eureka-server-1.0.0.jar
java -jar -Dspring.profiles.active=8763 rookie-eureka-server-1.0.0.jar
```

然后分别访问http://eureka.server8761.com:8761/,http://eureka.server8762.com:8762/,http://eureka.server8763.com:8763/。查看是否都启动成功

#### eureka client集群服务提供者

分别打开三个命令行窗口，分别运行下面三个命令

```java
java -jar -Dspring.profiles.active=8001 rookie-eureka-payment-provider-1.0.0.jar
java -jar -Dspring.profiles.active=8002 rookie-eureka-payment-provider-1.0.0.jar
java -jar -Dspring.profiles.active=8003 rookie-eureka-payment-provider-1.0.0.jar
```

#### eureka client集群服务消费者

分别打开一个命令行窗口，分别运行下面命令

```java
java -jar -Dspring.profiles.active=multi rookie-eureka-order-consumer-1.0.0.jar
```

在OrderConfig中国加入@LoadBalanced注解进行负载均衡，如下代码

```java
@Configuration
public class OrderConfig {


    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
```

访问http://localhost/consumer/payment/get/1可以看到端口交替出现

## 服务发现

以rookie-eureka-payment-provider为例进行示例，

在启动类中添加@EnableDiscoveryClient注解，然后再PaymentController中添加如下方法

```
      @Resource
    private DiscoveryClient discoveryClient;
  
  @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: "+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }
```

## Eureka自我保护

概述保护模式主要用于一组客户端和Eureka Server之间存在网络分区场景下的保护。一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务。 如果在Eureka Server的首页看到以下这段提示，则说明Eureka进入了保护模式：EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE       

























