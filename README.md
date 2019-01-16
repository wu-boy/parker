# Parker
记得小时候看过一个叫水果城的动画片，里面有个叫Parker的香蕉很厉害，他有一个神奇的背包，里面有各种各样的工具，每次遇到困难就是它发挥作用，什么都有。

本项目将基于Spring Boot集成各种开发框架，并提供各种工具类和应用实例，以便工作使用，因此取名Parker。
## areadata模块
将js文件中的行政区划数据以树形结构保存到数据库，并提供REST服务查询行政区划。
数据来源https://github.com/dwqs/area-data.git。
实现过程如下：
1、取出pcaa.js中的行政区划数据放入新建的area-data.txt中；
2、编写程序读取txt并将数据组织成树形结构保存至数据库。
## common模块
提供各种工具类和应用实例。
## config模块
提供Spring Boot通过属性文件外置配置的例子。
## easypoi模块
Spring Boot集成EasyPoi例子。
## feign模块
Spring Boot集成Feign例子。
## jpa模块
Spring Boot集成JPA例子。
## jpa-mybatis模块
Spring Boot集成JPA和MyBatis例子。
## mybatis模块
Spring Boot集成MyBatis例子。
## redis模块
Spring Boot集成Redis例子。
## rest模块
Spring Boot发布REST服务并集成Swagger例子。
## schedule模块
Spring Boot定时任务例子。
## shiro-base模块
Spring Boot集成Shiro例子。
## shiro-stateless模块
Spring Boot集成Shiro无状态应用例子。
## websocket模块
Spring Boot集成websocket例子。

