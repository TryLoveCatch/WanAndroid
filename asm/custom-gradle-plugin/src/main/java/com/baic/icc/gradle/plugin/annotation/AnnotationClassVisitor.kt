package com.baic.icc.gradle.plugin.annotation

import com.baic.icc.gradle.plugin.base.BaseClassVisitor
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor

class AnnotationClassVisitor(api: Int, classVisitor: ClassVisitor): BaseClassVisitor(api, classVisitor) {

    private val annotationClass = "Lcom/baic/icc/annotation/TestAsm;"
    private var isHit = false

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        if (annotationClass == descriptor) {
            isHit = true
        }
        return super.visitAnnotation(descriptor, visible)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        /**
         * 目标注解修饰
         */
        if (isHit) {
            /**
             * 排除native方法
             * 排除Synthetic方法
             * 排除抽象方法
             * 排除构造函数
             * 排除静态代码块
             */
            if (!isNative(access) && !isSynthetic(access) && !isAbstract(access) && "<init>" != name && "<clinit>" != name) {
                log("name: $name")
                return AnnotationAdviceAdapter(
                    api,
                    methodVisitor,
                    access,
                    name,
                    descriptor,
                    className
                )
            }
        }

        return methodVisitor
    }
}