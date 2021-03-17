**easy-code**

------

###  easy-code 是什么？

easy-code 是一个代码生成器，跟以往的代码生成器不同，支持多个数据库，并采用前后端分离的方式作为生成代码的方式，基于RBAC的角色访问控制，支持各种角色用户访问。现已开发**数据源管理**、**生成管理**、**权限管理**，后续将继续完善此项目。

### 项目访问

浏览器内输入  [https://code.yizhicheng.top](https://code.yizhicheng.top/)

用户名: **admin** 密码:**passw0rd**

------

### 项目目录

```
|____docker
| |____Dockerfile
|____MongoDB
| |____docker安装mongodb.pdf
|____sh
| |____build.sh
| |____package.sh
|____MySQL
| |____easy_code.sql
```

- Docker下为构建项目需要的Dockerfile
- MongoDB下为如何快速搭建一个MongoDB服务器
- sh下为服务器上执行的shell脚本
- MySQL下为项目需要的SQL脚本

------

### 技术架构

#### 后端

|     技术     |       备注        |                  官网                  |
| :----------: | :---------------: | :------------------------------------: |
|  SpringBoot  | 基于Java的脚手架  |           https://spring.io            |
| Mybatis-Plus | 简化开发的ORM框架 |        https://mp.baomidou.com         |
|    MySQL     |   关系型数据库    |         https://www.mysql.com          |
|    redis     |    缓存数据库     |            https://redis.io            |
|   MongoDB    |    文档数据库     |        https://www.mongodb.com         |
|  FreeMarker  |     模板引擎      |     https://freemarker.apache.org      |
|    lombok    |     简化开发      | https://github.com/rzwitserloot/lombok |
|    hutool    |    Java工具类     |         https://www.hutool.cn          |
|  PageHelper  |     分页插件      |      https://pagehelper.github.io      |
|     JWT      |    分布式token    |             https://jwt.io             |
|   knife4j    |     接口文档      |       https://doc.xiaominfo.com        |
|    Nginx     |     web服务器     |            http://nginx.org            |
|    Docker    |   应用容器引擎    |         https://www.docker.com         |
|    Linux     |     操作系统      |         https://www.linux.org          |

------

### 二次开发

如果要对代码进行二次开发，版本请尽量和下面保持一致

- JDK 8
- Maven 3.X
- MySQL 5.X

------

待续。。。。。。

