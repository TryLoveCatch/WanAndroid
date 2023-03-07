package com.baic.icc.gradle.plugin

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.variant.VariantInfo
import com.android.build.gradle.internal.pipeline.TransformManager
import com.baic.icc.gradle.plugin.annotation.AnnotationClassVisitor
import com.baic.icc.gradle.plugin.base.BaseTransform
import org.gradle.api.Incubating
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import java.io.InputStream
import java.io.OutputStream

class CustomTransform(val project: Project): BaseTransform(true) {

    // 指定 Transform 的名称，该名称还会用于组成 Task 的名称
    // 格式为 transform[InputTypes]With[name]For[Configuration]
    override fun getName(): String {
        return "customTransform"
    }

    // 是否支持增量构建
    override fun isIncremental() = true

    // 用于过滤 Variant，返回 false 表示该 Variant 不执行 Transform
    @Incubating
    override fun applyToVariant(variant: VariantInfo?): Boolean {
        return "debug" == variant?.buildTypeName
    }

    // 指定输入内容类型
    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    // 指定消费型输入内容范畴
    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    // 转换方法
//    override fun provideFunction(): (InputStream, OutputStream) -> Unit {
//        return { ios: InputStream, os: OutputStream ->
//            ios.copyTo(os)
//        }
//    }
    override fun provideFunction() = { ios: InputStream, os: OutputStream ->
        val classReader = ClassReader(ios)
        val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)
        val classVisitor: ClassVisitor = AnnotationClassVisitor(Opcodes.ASM7, classWriter)
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
        os.write(classWriter.toByteArray())
    }


}