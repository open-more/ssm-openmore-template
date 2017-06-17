# ssm-openmore-template

## 介绍
**Ssm-openmore-Template** 是基于SSM的J2EE项目快速开发脚手架，集成了最常用的框架,适用于`Restfull` 架构风格`Web Service`接口开发。

## 主要框架
* **Spring4.2**
* **Springmvc4.2**
* **Mybatis3.3.0**
* **Shiro**
* **c3p0连接池**
* **slf4j**
* **spring-fox**
* **asccidoctor**

## 工具框架
* **Spring-Test** 
* **Mybatis-Pagehelper** 
* **Mybatis通用Mapper3** 

## 开发工具
### IDE
`Intellij Idea`
### 依赖管理工具
`Gradle`

## 使用
``` shell
# git clone https://github.com/ichenkaihua/ssm-openmore-template.git
```

## 快速开始
配置mysql数据库，修改`environment/dev/jdbc.properties`文件。
```
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://{YOUR_DB_URL}/{DB_NAME}?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8
jdbc.username={USER_NAME}
jdbc.password={PASSWORD}
```
然后进入命令行:

### 进入项目目录
```shell
# cd ssm-openmore-template/
```
### 初始化环境为开发环境
```shell
# ./gradlew initEnv -Penv=dev
start initial environment
拷贝：environment/dev下的文件到src/main/resources/properties/  DONE
:initEnv UP-TO-DATE
BUILD SUCCESSFUL
```

### 初始化数据库,将db/migration目录下sql迁移到本地数据库
```shell
# ./gradlew flywayMigrate
```

### jetty启动项目
```shell
# ./gradlew  appStart
```

### 获取所有用户
```shell
# curl  http://localhost:8080/user
```

### 其他操作...

### 关闭jetty
```shell
# ./gradlew appStop
```

## 生成restAPI文档
项目启动后，打开`http://localhost:8080/swagger-ui.html`即可查看api文档

``` shell
# gradle asciidoctor
```

## 部署
集成了`gretty`插件,更多使用方法前往[gretty官网]
### jetty启动项目，[http://locaohost:8080/](http://locaohost:8080/)
```shell
# ./gradlew  jettyStart
```
###关闭jetty
```shell
# ./gradlew jettyStop
```



