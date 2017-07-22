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

Http Header请求参数如下：
* sign：签名（签名算法见下面）
* Content-Type：application/json
* timestamp：时间戳（秒）
* nonce：6位随机数
* app_key：App key
* encrypt：1表示内容加密，参数不存在或为其它值，表示内容不加密

## 签名算法
app_key表示分配给客户端的key，社个Key对应一个secret_key，签名时使用secret_key进行加密
```
sign = md5(secret_key + nonce + 请求方式（GET/PUT/POST/DELETE，必须大写）+ 请求接口URI（除域名后的URL） + body + timestamp)
```
####例如：
```
GET http://api.openmore.org/user/123
secret_key = a92664b406ed5b18dd04cd59c6778519
nonce = 1A39CJ
timestamp = 1497723214
body = 空
拼接字符串为：a92664b406ed5b18dd04cd59c67785191A39CJGET/user/1231497723214
md5("a92664b406ed5b18dd04cd59c67785191A39CJGET/user/1231497723214") = 4E22D478672492EE8914E2314FE575AF 
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


### 通过MybatisGenerator根据DB表结构，生成Mapper文件和对应的entity
```java
# ./gradlew mybatisGenerate
```
可以看到在dao包和entity包下出了自动生成的代码

### 关闭jetty
```shell
# ./gradlew appStop
```

## 查看在线restAPI文档
项目启动后，打开[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)即可查看api文档


### 通过代码生成器生成DTO（POJO），Servcie， ServiceImpl，Controller及对应的文档
 * 浏览器打开[http://localhost:8080/dto/home](http://localhost:8080/dto/home)，进入Dto生成器界面
 * 输入包名，DTO的英文名，与DB Entity对应及DTO的中文和描述
 * 输入需要创建DTO的属性，type为Java的基本数据类型
 * 分别显示Show*按钮，查看生成的代码
 * 点击Create All Source，将代码自动生成在包名目录下

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

## 单元测试
安装Junit GeneratorV2.0插件，然后在要添加单元测试的方法上右键，选择Generate->Junit Test-> V4，自动生成单元测试代码，默认代码生成在src/java/test包下，我们需要修改这个路径，系统设置Other Settings，自己指定下路径即可。

## 执行测试
```
./gradlew test
```
如果测试失败的话，会在目录下生成测试报告：ssm-openmore-template/build/reports/tests/test/index.html

## 常见问题
* nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping
```
mybatis自动生成的Entity代码里没有通过@Id来注解出id主键字段来，导致在selectByPrimaryKey出错
解决方法：在id字段前加@Id注解
```

