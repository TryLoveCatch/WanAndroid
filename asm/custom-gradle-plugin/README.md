# 自定义plugin
## 新建一个CustomPlugin
```kotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        println()
        println("########################################################################")
        println("##########                                                    ##########")
        println("##########                Custom Plugin                       ##########")
        println("##########                                                    ##########")
        println("########################################################################")
        println()
    }
}
```

## 创建resources
- 在main目录下，与java同级目录，创建resources目录
- 在resources目录下，创建META-INF目录
- 在META-INF目录下，创建gradle-plugins目录

## 创建properties
在gradle-plugins目录下，创建一个自定义的 xxx.properties

这个xxx，就是插件的名字，后续使用就需要：
```groovy
apply 'xxx'
```

咱们这里创建的名字为com.baic.icc.gradle.plugin.customPlugin.properties

## properties增加内容

```properties
implementation-class=xxx
```
这个xxx是我们自定义插件的全限定名，就是咱们实现了Plugin<Project>接口的类的全限定名

咱们这里的路径是
```properties
implementation-class=com.baic.icc.gradle.plugin.CustomPlugin
```

## 发布jar包
点击右侧gradle任务列表的publish即可

## 修改工程目录的setting.gradle
```groovy
pluginManagement {
    repositories {
        maven { url './repo' }
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url './repo' }
        google()
        mavenCentral()
    }
}
```

增加`maven { url './repo' }`

## 修改工程目录的build.gradle
```groovy
buildscript {
    dependencies {
        /**
         * 自定义gradle插件依赖
         */
        classpath "com.baic.icc.gradle.plugin:test-asm:1.0.0"
    }
}
```
这个classpath是刚才发布的jar包

## 修改app目录的build.gradle（也就是使用这个插件的module）
```groovy
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'com.baic.icc.gradle.plugin.customPlugin'
}
```
增加 `id 'com.baic.icc.gradle.plugin.customPlugin'`
这个id，就是前面创建的properties的文件名

## 测试

到此已经配置完成了，我们同步一下gradle，可以在Build里面看到我们的打印：
![](../extras/3.png)

# 问题

## "Could not find com.android.tools.build:gradle:7.2.0
我现在修改成依赖4.0.2版本了。
猜测原因应该是我们本地是java1.7，应该需要依赖java11

## Build was configured to prefer settings repositories over project repositories but repository 'Gradle Libs' was added by unknown code

需要修改 setting.gradle，删除 repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

```groovy
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
```




## publish之后，出现了两个库

如下图所示
![](../extras/1.png)

竟然出现了两个库！！！我们再看下右侧的gradle的task
![](../extras/2.png)

可以看到除了咱们自定义的maven任务，还多了一个pluginMaven名字相关的task，到底是哪里引入了呢？
在custom-gradle-plugin下的build.gradle里面
```groovy
plugins {
    id 'java-library'
    id 'org.jetbrains.kotlin.jvm'
    id 'java-gradle-plugin'
}
```
我们引入了一个 java-gradle-plugin的插件，这个的作用类似于maven-publish，相当于我们依赖了两个publish插件，
这里我们直接去掉java-gradle-plugin就可以了