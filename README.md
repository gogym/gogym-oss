# gogym-oss

本项目是本屌丝用小半个月业余时间捣鼓的。第一个版本比较粗糙，后续有时间会继续更新完善

本项目是一个纯技术中间件，主要是对fastdfs的管理，适合于对文件系统有需要，不想花人力开发，也不想使用云服务的中小企业

fastdfs是阿里巴巴开源的，用C语言写的一个优秀的文件系统，具体的介绍网上有。

但开源版本只是一个可部署在Linux下的文件系统，本身没有提供管理平台，也没有太多的SDK。有java版本的sdk也比较简陋。只是简单上传下载。

本项目主要提供对fast提供管理功能，目前实行的功能主要有：

1、用户的注册与登录

2、可创建对应的应用记录，并对应用简单的管理。

3、对javaSDK添加了fastdfs连接池，提供了上传下载的效率

4、提供了简单的上传下载api。并且可开启api访问鉴权

5、提供了文件的简单管理功能。对每个应用的文件进行了分类管理

6、提供了简单的文件，空间，请求次数的统计功能

7、已经集成了sping cloud,可以很容易的做到高可用


使用框架：

后台：java、spring boot、spring cloud、beetlsql、mysql、fastdfs

管理平台：node.js、layui等

![image](https://github.com/gogym/gogym-oss/blob/master/1.png)

![image](https://github.com/gogym/gogym-oss/blob/master/2.png)

![image](https://github.com/gogym/gogym-oss/blob/master/3.png)

![image](https://github.com/gogym/gogym-oss/blob/master/4.png)
