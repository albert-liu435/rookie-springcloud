rabbitmq

## rabbitmq windows安装

安装erlang

[下载地址](https://erlang.org/download/)

## 安装rabbitmq

[下载地址](https://dl.bintray.com/rabbitmq/all/rabbitmq-server)

打开命令行窗口，进入到sbin目录，执行如下命令安装可视化插件

```java
rabbitmq-plugins enable rabbitmq_management
```

然后执行如下命令启动

```java
C:\developer\mq\rabbitmq_server-3.8.6\sbin>rabbitmq-server.bat
```

访问http://127.0.0.1:15672/地址，然后输入账号和密码，分别为guest,guest即可看到启动成功