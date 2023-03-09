package com.baic.icc.gradle.plugin.bitmap

import com.baic.icc.gradle.plugin.base.BaseClassVisitor
import org.objectweb.asm.ClassVisitor

/**
 * val imageView = AppCompatImageView(context)
 * 对这种是可以的
 * 但是 val imageView = ImageView(context)
 * 就无能为力了
 * @property imageViewClass String
 * @property monitorImageViewClass String
 * @constructor
 */
class BitmapLegalClassVisitor(api: Int, classVisitor: ClassVisitor): BaseClassVisitor(api, classVisitor) {
    private val imageViewClass = "android/widget/ImageView"
    private val monitorImageViewClass = "com/tlc/wanandroid/ui/view/MonitorImageView"

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        if (name != monitorImageViewClass && superName == imageViewClass) {
            super.visit(version, access, name, signature, monitorImageViewClass, interfaces)
        } else {
            super.visit(version, access, name, signature, superName, interfaces)
        }
    }

}