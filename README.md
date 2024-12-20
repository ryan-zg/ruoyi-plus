<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">RuoYi-Plus v1.0.0</h1>
<h4 align="center">基于SpringBoot+Vue前后端分离的Java快速开发框架</h4>

### 前言
本人大四，刚开始准备毕设，想着用一个开源的功能强大的框架开发一个项目平台。有一个框架帮助搭建环境以及后续辅助业务开发应该是一个不错的选择，同时虽然学校对于项目本身并不在意而在报告。

之前用的是`RuoYi`的前后端分离的框架，总体很棒，不过还是有些地方对我这样的懒人来说稍微费点事，比如用了`mybatis`而不是`mybatis-plus`，以及说表的创建能不能也在页面上完成，不想在软件之间来回切换，虽然大部分情况下表是让`chatgpt`生成的，想着能不能添加一些新的功能和模块以满足我的需求。

看了`RuoYi`的许可是`MIT`，所以就直接在这上面进行修改了。核心的思想是尽量不修改原有的代码，而是添加新的功能。

### 若依 RuoYi
[官网](https://gitee.com/y_project/RuoYi)

### 添加功能日志
* 2024-11-24
  * 原有的mybatis修改为mybatis-plus
  * 页面添加数据库表生成页面，类似于`navicat`这样的数据库工具
  * 添加`hutool`工具依赖

### 后续更新
在做毕设的期间应该会不定时更新以添加新的功能或者优化。之后应该会考虑修改微服务的版本。

### 许可
可以随意使用和修改。
