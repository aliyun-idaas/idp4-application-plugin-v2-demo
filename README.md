# idp4-application-plugin-v2-demo
应用插件实现demo

开源协议：[GPL-3.0 License](https://github.com/aliyun-idaas/idp4-application-plugin-v2-demo/blob/main/LICENSE) 。

## 开发环境要求
- Java  v1.8+
- Maven 3.3+
- 字符编码：UTF-8

## 使用框架与版本
- com.idsmanager.idp.core v4.16.0-mysql
- log4j 1.2.14
- testng 6.1.1

> com.idsmanager.idp.core不对外提供,如需要,请联系项目经理单独提供

## 如何使用
> 前提：需要有部署IDaas的实例。
1. clone工程到本地开发环境（需要安装Maven, JDK）。
2. maven打包: `mvn clean package`
3. 在IDaas中以IT管理员登录，在`其他管理`-`插件管理`-`应用插件`,上传生成的jar文件:`target\idp-plugin-v2-demo-1.0.0-jar-with-plugin.jar`
4. 在`应用`-`添加应用` 找到`DemoV2`应用,点击添加应用即可

## 更多帮助
若需要更多帮助，可访问部署的IDaas实例的开发者文档中的'应用模板插件'内容。