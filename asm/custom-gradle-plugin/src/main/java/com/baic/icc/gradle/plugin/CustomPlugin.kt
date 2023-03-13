package com.baic.icc.gradle.plugin

import com.android.build.gradle.BaseExtension
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


        /**
         * https://segmentfault.com/a/1190000041856822
         * 插件扩展机制
         * Extension 扩展是插件为外部构建脚本提供的配置项，用于支持外部自定义插件的工作方式，
         * 其实就是一个对外开放的 Java Bean 或 Groovy Bean。
         * 例如，我们熟悉的 android{} 就是 Android Gradle Plugin 提供的扩展。
         */
        // applyExtension(project)

        /**
        * 参考：https://henleylee.github.io/posts/2018/9986453e.html
         *
         * AppExtension 用于构建 Android app module 的 com.android.application 插件扩展。
         * LibraryExtension 用于构建 Android library module 的 com.android.library 插件扩展。
         * TestExtension 用于构建 Android test module 的 com.android.test 插件扩展。
         * FeatureExtension 用于构建 Android Instant Apps 的 com.android.feature 插件扩展。
         */
        // 获取 Android 扩展
        val androidExtension = project.extensions.getByType(BaseExtension::class.java)
        // 注册 Transform，支持额外增加依赖
        androidExtension.registerTransform(CustomTransform(project))
    }

//    private fun applyExtension(project: Project) {
//        // 创建扩展，并添加到 ExtensionContainer 容器
//        project.extensions.create()
//    }

}