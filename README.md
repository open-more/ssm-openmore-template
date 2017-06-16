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
# git clone https://github.com/ichenkaihua/ssm-easy-template.git
```

## 快速开始
新建或者配置一个mysql数据库，根据数据库信息修改`src/main/resources-dev/jdbc-mysql.properties`文件。
然后进入命令行:

```shell
# 进入项目目录
# cd ssm-openmore-template/

# 初始化数据库,将db/migration目录下sql迁移到本地数据库
# ./gradlew flywayMigrate

# jetty启动项目
# ./gradlew  appStart

# 获取所有用户 
# curl  http://localhost:8080/users

# 其他操作...

# 关闭jetty
# ./gradlew appStop
```

## 生成restAPI文档
项目启动后，打开`http://localhost:8080/swagger-ui.html`即可查看api文档

``` shell
# 生成html和pdf文档
# gradle asciidoctor
```

## 部署
集成了`gretty`插件,更多使用方法前往[gretty官网]
```shell
# jetty启动项目，http://locaohost:8080/
# ./gradlew  jettyStart

#关闭jetty
# ./gradlew jettyStop

```



