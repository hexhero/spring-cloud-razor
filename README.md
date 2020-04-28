# Spring Cloud Razor 

<div align="center"><img src="docs/banner.png"/></div>

![GitHub last commit](https://img.shields.io/github/last-commit/yangb92/spring-cloud-razor) ![Build passing](https://img.shields.io/badge/build-passing-green)

**:construction: 工程正在建设中, 请持续关注最新动态..**

> Entities should not be multiplied unnecessarily -Occam's Razo 

## 简介

Spring Cloud Razor 是基于 Spring Cloud 的微服务整合方案，秉承奥卡姆剃刀原理 "如无必要,勿增实体" 设计思想，本套微服务架构中唯一依赖的外部服务 —— Mysql数据库，
因此方便上手，易于使用。

### 功能介绍

**(1) 微服务功能**
* [x] 服务授权/令牌传递 (OAuth2 + JWT)
* [x] 服务注册/调用 (Eureka + OpenFeign)
* [x] 服务网关/负载均衡 (Spring Cloud Gateway + Ribbon)
* [x] 服务降级/熔断 (Hystrix)

**(2) 业务模块功能**
* [x] mybatis增强 (mybatis + tk.mybatis)
* [x] 分页 (PageHelper)
* [x] 代码生成 (Maven generator 插件生成 dao,entity,mapper.xml)

### 项目结构

```
microservice
  | business-order      # 订单模块
  | business-payment    # 支付模块
  | serve-discover      # 服务发现
  | serve-gateway       # 网关服务
  | serve-oauth2        # OAuth2认证中心
  | api-common          # 存放共享实体和封装组件等
```
订单和支付模块用于框架功能演示，实际情况请根据业务进行变更。

* `business-*` 是业务模块，根据具体的业务量扩展变更
* `serve-*` 服务模块，为业务模块提供通信，入口，鉴权等等服务。

## 快速上手

### 1. 环境准备

**(1) 初始化数据库**

安装 Mysql，创建数据库`develop`，

* 字符集: utf8mb4
* 排序规则: utf8mb4_bin

执行 [table-schema.sql](docs/table-schema.sql)。

**(2) 使用 git 下载项目**

```bash
git clone git@github.com:yangb92/spring-cloud-razor.git
```

**(3) IDEA 导入项目**

`File` - `New` - `Project from Existing Source` - `选择项目目录` - `Maven Project` 

等待项目加载完成

### 2. 设置

在本机的`hosts`文件中添加如下配置
```
127.0.0.1  serve-discover
127.0.0.1  serve-gateway
127.0.0.1  serve-oauth2
# 数据库连接ip地址
127.0.0.1  mysql-server
127.0.0.1  business-order
127.0.0.1  business-payment
```

修改各个项目 resource 目录中 application.yml 配置文件中的数据库用户名和密码

```yaml
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.jdbc.Driver
      url: jdbc:mysql://mysql-server:3306/develop?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
      username: root
      password: '123'
```

### 3. 运行测试

**(1) 运行**

项目启动顺序:

1. serve-discover
2. serve-gateway
3. serve-oauth2
4. business-payment
5. business-order

**(2) 测试**

使用 PostMan 工具测试接口：

申请令牌: `POST` http://localhost/oauth/token?client_id=client&client_secret=123&grant_type=password&username=yb&password=123

```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzXzEiXSwidXNlcl9uYW1lIjoie1wiZnVsbG5hbWVcIjpcIuadqOaWjFwiLFwiaWRcIjoxLFwibW9iaWxlXCI6XCIxNTAwMDAwMDAwMFwiLFwidXNlcm5hbWVcIjpcInliXCJ9Iiwic2NvcGUiOlsiYXBwIl0sImV4cCI6MTU4Nzc4NDM0NywiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiI1YTNiZDljYi0wYzk4LTQwYjgtYTZmMS0zM2RiMjZjZDc1NzAiLCJjbGllbnRfaWQiOiJjbGllbnQifQ.BmaafjwbBTJAZDJWDKHbiSs0B1AuE3wUKMVrMBpKE2s",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzXzEiXSwidXNlcl9uYW1lIjoie1wiZnVsbG5hbWVcIjpcIuadqOaWjFwiLFwiaWRcIjoxLFwibW9iaWxlXCI6XCIxNTAwMDAwMDAwMFwiLFwidXNlcm5hbWVcIjpcInliXCJ9Iiwic2NvcGUiOlsiYXBwIl0sImF0aSI6IjVhM2JkOWNiLTBjOTgtNDBiOC1hNmYxLTMzZGIyNmNkNzU3MCIsImV4cCI6MTU4ODAzNjM0NywiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiIyYzcyNTRhYS1jOTBjLTQ0NzgtYWQ3My03NTU2OWI2NTcwZWQiLCJjbGllbnRfaWQiOiJjbGllbnQifQ.EmFeyQWhQbiLmfUYKSqyt6d5IbKhyIMAHr7oE3Ou-nI",
    "expires_in": 7199,
    "scope": "app",
    "jti": "5a3bd9cb-0c98-40b8-a6f1-33db26cd7570"
}
```

调用Order服务： `GET` http://localhost/order/admin

在请求头加入令牌
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzXzEiXSwidXNlcl9uYW1lIjoie1wiZnVsbG5hbWVcIjpcIuadqOaWjFwiLFwiaWRcIjoxLFwibW9iaWxlXCI6XCIxNTAwMDAwMDAwMFwiLFwidXNlcm5hbWVcIjpcInliXCJ9Iiwic2NvcGUiOlsiYXBwIl0sImV4cCI6MTU4Nzc4NDM0NywiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiI1YTNiZDljYi0wYzk4LTQwYjgtYTZmMS0zM2RiMjZjZDc1NzAiLCJjbGllbnRfaWQiOiJjbGllbnQifQ.BmaafjwbBTJAZDJWDKHbiSs0B1AuE3wUKMVrMBpKE2s
```

```json
{
    "status": 200,
    "success": true,
    "message": "成功",
    "data": {
        "id": 1,
        "username": "yb",
        "password": null,
        "fullname": "杨斌",
        "mobile": "15000000000"
    }
}
```

### 4. 使用Docker部署

打包
```bash
mvn clean package
```

在项目跟目录执行下面命令发布到docker容器
```bash
docker-compose up
```

## API

### 1. 在 Controller 中获取当前登陆用户

只需要将 `AppUser` 对象放在参数列表，并且加上 `@CurrentUser` 注解，参数解析器负责将当前登陆用户注入到该参数中。
```java
@RequestMapping("/order/admin")
public ResultVo admin(@CurrentUser AppUser user){
    return ResultVo.makeSuccess(user);
}
```

### 2. 结果视图对象 `ResultVo`

`ResultVo` 提供了便于使用静态方法，方便开发者快速构建一个结果对象。
```java
public ResultVo admin(@CurrentUser AppUser user){
    return ResultVo.makeSuccess(user);
}
```
结果
```json
{
    "status": 200,
    "success": true,
    "message": "成功",
    "data": {
        "id": 1,
        "username": "yb",
        "password": null,
        "fullname": "杨斌",
        "mobile": "15000000000"
    }
}
```

### 3. 方法权限

用户的权限定义在 `app_permission` 表

|id|code|description|url|
|---|---|---|---|
|1|ROLE_ADMIN|管理员|/admin/user|

在方法上使用 `@Secured("ROLE_ADMIN")` 注解标记方法的权限
```java
@RequestMapping("/order/admin")
@Secured("ROLE_ADMIN")
public ResultVo admin(@CurrentUser AppUser user){
    return ResultVo.makeSuccess(user);
}
```

### 4. 服务调用

使用 `OpenFeign` 调用远程服务。

虚拟场景： 

用户A向 Order(订单模块) 发出查询请求，查询订单的支付流水。 Order 查询到订单后调用 Payment（支付）模块获取支付流水号

流程：用户A -> order -> payment

Payment 模块查询支付流水接口:
```java
@GetMapping("/payment/{id}")
public ResultVo<Payment> getPayment(@PathVariable Long id){
    Payment payment = paymentService.findById(id);
    return ResultVo.makeSuccess(payment);
}
```
Order 创建 Feign Service 接口 Payment 支付流水查询接口
```java
@FeignClient("PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/{id}")
    ResultVo<Payment> getPayment(@PathVariable("id") Long id);
}
```
Order 创建订单查询Service
```java
@Autowired
private PaymentFeignService paymentFeignService;

@Override
public ResultVo getOrderDetails(AppUser user) {
    ResultVo<Payment> payment = paymentFeignService.getPayment(1L);
    return ResultVo.makeSuccess(user.getFullname() + " 您的订单信息:xxx " + "支付流水: " + payment.getData().getSerial());
}
```
Order 提供订单查询接口
```java
@RequestMapping("/order/details")
public ResultVo myOrderPay(@CurrentUser AppUser user){
    return orderService.getOrderDetails(user);
}
```

测试

`GET` http://localhost/order/details

```json
{
    "status": 200,
    "success": true,
    "message": "杨斌 您的订单信息:xxx 支付流水: 20200422100238",
    "data": null
}
```

### 5. 服务降级/熔断

定义回调方法,在业务方法上加`@HystrixCommand(defaultFallback = "commonFallback")` 并填写回调方法属性

更多使用方式请参考该文档: <http://book.yangb.xyz/program/SpringCloud/hystrix.html>

```java

@Autowired
private PaymentFeignService paymentFeignService;

@HystrixCommand(defaultFallback = "commonFallback")
@Override
public ResultVo getOrderDetails(AppUser user) {
    ResultVo<Payment> payment = paymentFeignService.getPayment(1L);
    return ResultVo.makeSuccess(user.getFullname() + " 您的订单信息:xxx " + "支付流水: " + payment.getData().getSerial());
}

public ResultVo commonFallback(){
    return ResultVo.makeFailed("服务暂不可用!");
}
```

### 6. mybatis插件 tk.mybatis 的使用

[tk.mybatis 使用文档](https://github.com/abel533/Mapper/wiki)

### 7. 分页插件 PageHelper 的使用

[PageHelper 使用文档](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md)

### 8. 代码生成插件 generator 的使用

[代码生成插件 使用文档](https://github.com/abel533/Mapper/wiki/4.1.mappergenerator)

## 关于作者

[@杨斌](http://yangb.xyz)

![Q群135821423](https://img.shields.io/badge/Q群-135821423-blue)
![QQ 523084685](https://img.shields.io/badge/QQ-523084685-blue)

## 版权信息

Copyright (c) 2020 杨斌 All rights reserved.

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="知识共享许可协议" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a>
