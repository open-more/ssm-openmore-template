Openmore 基于SSM（Spring+SpringMVC+Mybatis）开源REST API服务
===============================
关于Openmore
------------
<p align="center">
    <a href="https://github.com/open-more" target="_blank">
        <img src="https://avatars0.githubusercontent.com/u/27731838?v=3&s=460" width="120" alt="Open More" />
    </a>
</p>
Openmore团队是目前北京的一家创业公司内里的几个主程自发组织的开源团队, 团队目标是将创业过程中技术团队遇到的技术经验进行开源分享, 本着更开放,更高效的原则帮助中国的移动开发者填坑,减少开发成本,同时吸收大家的意见与建议。

## 介绍
**ssm-openmore-template** 是基于SSM的J2EE项目快速开发脚手架，集成了最常用的框架,适用于`Restfull` 架构风格`Web Service`接口开发。

## 主要框架
* **Spring4.2**
* **Springmvc4.2**
* **Mybatis3.3.0**
* **Shiro**
* **c3p0**
* **slf4j**
* **spring-fox**
* **asccidoctor**

## 工具框架
* **Spring-Test** 
* **Mybatis-Pagehelper** 
* **Mybatis通用Mapper3** 

## 开发工具
`Intellij Idea`
### 依赖管理工具
`Gradle`

## 使用
``` shell
# git clone https://github.com/open-more/ssm-openmore-template.git
```

## 接口安全（拦截器实现）
* 防止篡改，所有请求接口都带有sign签名，sign = md5(app_secret + nonce + method + uri + body(json) + timestamp)
* 防止重放攻击，Header里带有时间戳，超过时间差容忍范围(默认60秒)，拒绝访问
* 关键数据可加密，Header中encrypt=1时，接口数据进行AES128加密，隐藏明文发送数据
* Header里带有DEVICE_TOKEN，通过token来控制客户端访问，比如：互踢，拉黑机制。

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

## 查看在线restAPI文档
项目启动后，打开[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)即可查看api文档

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



