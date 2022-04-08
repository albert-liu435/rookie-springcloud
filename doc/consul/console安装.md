console安装

consul是一套开源的分布式服务发现和配置管理系统，由HashiCorp公司用Go语言开发。

提供了微服务中的服务治理、配置中心、控制总线等功能。这些功能中的每一个都可以根据需要单独使用，也可以一起使用以构建全方位的服务网格，总之Consul提供了一种完整的服务的服务网格解决方案。

它具有很多优点。包括：基于raft协议，比较简单；支持健康检查，同时支持HTTP和DNS协议支持跨数据中心的WAN集群提供图形界面跨平台，支持Linux、Mac、Windows。

### consul安装

1、去官网下载

[下载地址](https://www.consul.io/downloads.html)

2、运行

windows版下载后解压直接运行，然后再目录下运行如下命令，使用开发模式运行

```java
consul agent -dev
```

然后访问localhost:8500即可。

### consul项目

创建rookie-springcloud-consul项目，并运行项目，然后访问http://localhost/consumer/payment/consul即可



CAP理论

CAP

C:Consistency 强一致性

A: Availability 可用性

P:Partition tolerance(分区容错)

CAP理论关注粒度是数据，而不是整体系统设计的策略

AP(Eureka):AP架构当网络分区出现后，为了保证可用性，系统B可以返回旧值，保证系统的可用性。结论：违背了一致性C的要求，只满足可用性和分区容错，即AP  

CP(Zookeeper/Consul):CP架构当网络分区出现后，为了保证一致性，就必须拒接请求，否则无法保证一致性结论：违背了可用性A的要求，只满足一致性和分区容错，即CP  







