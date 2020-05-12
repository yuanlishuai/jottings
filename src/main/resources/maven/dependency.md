# MAVEN 

## 命令
 * mvn clean  install -U  ：  `-U`参数强制让Maven更新

## 依赖范围 
   * compile ：编译依赖范围，如果没有指定则默认此范围-对编译、测试、运行都有效
   * test ：测试依赖范围，此依赖范围只对此时classPath 有效，在编译和运行时无法使用
   * provided ： 已提供依赖范围，此依赖范围对于编译和测试有效，但是对于运行无效
   * runtime ：运行时依赖范围， 对于测试和运行时有效，但是对于编译主代码时无效
   * system ： 系统依赖范围，该依赖范围和 `provided`  完全一样。
      但是使用的时候必须通过systemPath元素显示的指定依赖文件路径，
      该依赖不通过maven仓库解析，而且往往与本机系统绑定，可能造成不可移植，谨慎使用
   * import(maven 2.9 +) : 导入依赖范围，该依赖范围不会对三种classPath产生任何影响  
   
 
> 最左边一列表示第一直接依赖范围   最上边一行表示第二直接依赖范围   

|          | compile | test   | provided| runtime |  
|  :---    | :---:   | :---:  |  :---:  |   :---: | 
| compile  | compile | ---    |  ---    |  runtime|  
| test     | test    | ---    |  ---    |  test   |   
| provided | provided| ---    |provided | provided|
| runtime  | runtime | ---    |  ---    | runtime |  

-------------------


## 依赖解调(Dependency Mediation)  

- 第一原则 ：路径最短者优先   
- 第二原则 ：第一声明者优先
  *   在依赖长度相等的前提下，在POM中定义的声明的顺序决定了谁会被解析使用，顺序靠前的那个依赖被使用  

## 优化依赖  
- 查看当前项目的已解析的依赖（直接依赖、传递性依赖） `mvn dependency:list`  
- 查看当前项目的已解析依赖的依赖树 `mvn dependency:tree`  
- 分析当前项目依赖 `mvn dependency:analyze`  
   查看分析结果可分为两大部分：  
    * `Used undeclared dependencied` ：指项目中使用到的但是没有显示声明的依赖，此种依赖没有显示声明，容易忽略，不易发现
    * `Unused declared dependencies` ：指项目中未使用的，但是显示声明的依赖。对于这种依赖也不能简单地删除，
      由于 `dependency:analyze` 只会分析编译主代码合测试代码需要用到的依赖，一些执行测试和运行时的依赖它发现不了   


---
 
## 私服  

* 私服优点
  - 节省自己的外网带宽
  - 加速Maven构件
  - 部署第三方构件
  - 提高稳定性，增强控制
  - 降低中央仓库的负荷  

## 远程仓库  
#### 配置  

```xml
<package> 
 ...
  <!-- repositories 元素下可声明多个元素 -->
   <repositories>
    <repository>
        <id>jboos</id>
        <name>JBoos Repository</name>
        <url>http://repository.jboos.com/maven2</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <!--  关闭SNAPSHOT 版本的下载      -->
            <enabled>false</enabled>
             <!-- updatePolicy 配置maven从远程仓库检查更新的频率 默认为： daily 表示每天检查一次
              可用值：
                daily：每天检查一次 （默认）
                never：从不检查
                always：每次构建都检查一次
                interval-X ： 每隔X分钟检查一个更新
              用户可添加参数  `-U` 来强制更新
             -->
            <updatePolicy>daily</updatePolicy>
            <!-- checksumPolicy 检查校验和文件的策略。 当构件被部署到Maven仓库时会同时不失对应的校验和文件，在下载时Maven会验证校验和文件
                 可选值：
                    warn ：执行构建时输出告警信息 （默认）
                    fail ：Maven遇到校验和错误就使构建失败
                    ignore：使Maven完全忽略检验和错误
             -->
            <checksumPolicy>ignore</checksumPolicy>
        </snapshots>
    </repository>
   </repositories>
</package>
```
#### 认证 
> 配置认证信息与配置仓库信息不同，仓库信息可直接配置在POM文件中，但认证信息必须在 `setting.xml` 中，
> 用 `servers`表示。 `server`元素的id必须与POM中序号认证的`repository`的 id 完全一致
  
==

## 生命周期和插件    
> Maven 的生命周期是抽象的，实际上都是由插件来完成。生命周期和插件两者协同工作，密不可分。
>例如针对编译的插件有 `maven-compiler-plugin`、针对测试的插件有`maven-surefire-plugin`   

### 生命周期   
Maven拥有三套相互独立的生命周期分别为 : 
- clean :清理项目  
    clean生命周期包含三个阶段  
    * pre-clean 执行一些清理前需要完成的工作
    * clean 清理上一次构建生成的文件
    * post-clean 执行一些清理后需要完成的工作
- default ：构建项目  
  default生命周期定义了真正构建时需要执行的所有步骤
    * validate
    * initialize
    * generate-sources
    * process-sources：处理项目主资源文件，一般来说，是对 src/main/resources 目录的内容进行变量替换工作后，复制到项目输出的主classpath目录中
    * compile ： 编译项目主代码一般来说，是编译 src/main/java 目录下的java文件到项目输出的主classpath目录中  
    * process-classes
    * generate-test-sources
    * process-test-sources ：处理项目测试资源文件
    * generate-test-resource
    * process-test-resources
    * test-compile : 编译项目测试代码
    * process-test-classes
    * test ：使用单元测试框架进行测试，但是测试代码不会进行打包活部署
    * prepare-package
    * package ：接受编译好的代码，打包成可发布的格式，如JAR
    * pre-integration-test
    * integration-test
    * post-integration-test
    * verify
    * install ：将打包好的文件安装到Maven本地仓库，供本地其他项目使用
    * deploy ： 将最终的包复制到远程仓库，供其他Maven项目使用  
- site ：建立服务站点
    site的生命周期是建立和发布项目站点，Maven能够基于POM所包含的信息，自动生成一个友好的站点，方便团队交流和发布项目信息
    * pre-site ： 执行一些在项目站点之前需要完成的工作
    * site ：生成项目站点文件
    * post-site ：执行以下在生成项目站点之后需要完成的工作
    * site-deploy ：将生成的项目站点发布到服务器上    


  


     
