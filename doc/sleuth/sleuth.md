sleuth

## 概述

在微服务框架中，一个由客户端发起的请求在后端系统中会经过多个不同的的服务节点调用来协同产生最后的请求结果，每一个前段请求都会形成一条复杂的分布式服务调用链路，链路中的任何一环出现高延时或错误都会引起整个请求最后的失败。

### 是什么

Spring Cloud Sleuth提供了一套完整的服务跟踪的解决方案

在分布式系统中提供追踪解决方案并且兼容支持了zipkin

### 解决

![img](.\pic\img.png)

## 搭建链路监控

### zipkin

#### 下载并运行

[下载地址](https://zipkin.io/pages/quickstart)

直接运行即可 如在cmd命令行运行如下命令

```java
java -jar  zipkin-server-2.23.16-exec.jar
```

然后访问http://localhost:9411/zipkin/

#### 完整的调用链路

表示一请求链路，一条链路通过Trace Id唯一标识，Span标识发起的请求信息，各span通过parent id 关联起来

![img_1](.\pic\img_1.png)

一条链路通过Trace Id唯一标识，Span标识发起的请求信息，各span通过parent id 关联起来

![img_2](.\pic\img_2.png)

![img_3](.\pic\img_3.png)

Trace:类似于树结构的Span集合，表示一条调用链路，存在唯一标识

span:表示调用链路来源，通俗的理解span就是一次请求信息



调用http://localhost/consumer/payment/zipkin，多发送几次，然后通过http://localhost:9411/zipkin/即可查看链路情况