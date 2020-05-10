## MAVEN 

### 命令
 * mvn clean  install -U  ：  `-U`参数强制让Maven更新

#### 依赖范围 
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


#### 依赖解调(Dependency Mediation)  

- 第一原则 ：路径最短者优先   
- 第二原则 ：第一声明者优先
  *   在依赖长度相等的前提下，在POM中定义的声明的顺序决定了谁会被解析使用，顺序靠前的那个依赖被使用  

 
#### 私服  

* 私服优点
  - 节省自己的外网带宽
  - 加速Maven构件
  - 部署第三方构件
  - 提高稳定性，增强控制
  - 降低中央仓库的负荷  

#### 远程仓库  

##### 配置  

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
##### 认证 
> 配置认证信息与配置仓库信息不同，仓库信息可直接配置在POM文件中，但认证信息必须在 `setting.xml` 中，
>用 `servers`表示。 `server`元素的id必须与POM中序号认证的`repository`的 id 完全一致


  