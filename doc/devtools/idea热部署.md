idea热部署

### 1、添加devtools依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
```

### 2、添加插件

添加插件到父工程或者当前的工程

```java
    <build>
       <!-- <finalName>你的工程名称</finalName>-->
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

### 3、开启自动编译权限

![1649233114](.\pic\1649233114.png)

### 4、更新相关的值

按住ctrl+shift+alt+/键并查找到Registry。

然后选中compiler.automake.allow.when.app.running，

actionSystem.assertFocusAccessFromEdt，

5、重启IDEA