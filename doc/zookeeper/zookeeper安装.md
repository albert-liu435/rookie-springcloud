zookeeper安装

[下载地址](https://archive.apache.org/dist/zookeeper/)

需要zookeeper 3.5版本以上，本文使用的是3.6.3

## windows安装部署

### 单机部署

1、下载apache-zookeeper-3.6.3-bin.tar.gz，本地解压；

2、在apache-zookeeper-3.6.3-bin/目录下创建data文件夹和log文件夹；

3、将apache-zookeeper-3.6.3-bin/conf下的文件zoo_sample.cfg复制一份到当前目录下，重命名为zoo.cfg；

4、打开zoo.cfg，配置dataDir及dataLogDir选项；
 dataDir=...\\zookeeper\\apache-zookeeper-3.6.3-bin\data
 dataLogDir=...\\zookeeper\\apache-zookeeper-3.6.3-bin\log
 【...代表zookeeper解压路径】

5、进入/bin目录下，打开cmd，输入命令zkCli.cmd，启动Zookeeper客户端，进行操作



