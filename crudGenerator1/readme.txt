crudGenerator
增删改查代码生成工具
使用freeMarker 结合tkMybatis生成代码 从前端jsp,js,到后端controller，service，pageModel
后端代码生成大部分已基本实现，
剩下前端功能

使用步骤
1安装lombok
eclipse 启动eclipse.ini 文件末尾添加-javaagent:lombok.jar
eclipse.ini同级目录放置一个lombok.jar文件
可以从maven仓库复制，需要重命名jar文件删除版本号，配置完毕后重启eclipse
参考：
lombok依赖：
<dependency>
		    <groupId>org.projectlombok</groupId>
		    <artifactId>lombok</artifactId>
		    <scope>provided</scope>
		</dependency>
参考文档：http://blog.csdn.net/v2sking/article/details/73431364

2.application.yml
参考application.yml内容

3.运行Application.java
即可生成 controller service pageModelParam等代码


待实现：
1.所有代码表集合到一个查询页面
2.表格展示字段可以控制是否显示
3.查询条件每行三个，超过三个换行
4.字段名称默认从表中获取注释，可以对字段名称起别名，
5.字段展示顺序排序
5.可自定义模板生成规则，开发者自己决定采用什么模板，默认使用自定义模板
6.优化generator 成员变量
7.结合mbg 一键生成

