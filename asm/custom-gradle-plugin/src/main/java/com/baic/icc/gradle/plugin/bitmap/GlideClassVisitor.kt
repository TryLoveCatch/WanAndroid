package com.baic.icc.gradle.plugin.bitmap

import com.baic.icc.gradle.plugin.base.BaseClassVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor


class GlideClassVisitor(api: Int, classVisitor: ClassVisitor): BaseClassVisitor(api, classVisitor) {
    private val imageViewTargetClass = "com/bumptech/glide/request/target/ImageViewTarget"
    private val setResourceMethod = "setResource"
    private var isHit = false

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        if (imageViewTargetClass == superName) {
            isHit = true
        }
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        if (isHit) {
            if (setResourceMethod == name) {
                return GlideAdviceAdapter(
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