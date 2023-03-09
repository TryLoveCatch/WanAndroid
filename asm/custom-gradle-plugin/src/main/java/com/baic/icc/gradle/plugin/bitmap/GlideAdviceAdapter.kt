package com.baic.icc.gradle.plugin.bitmap

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

/**
 * https://moonshot.live/2018/08/28/Image-Detector-Plugin/
 *
 * 1、kotlin的类是GlideHelper，我们使用的全局静态函数，生成的java代码会包裹一个类GlideHelperKt，这个需要注意
 * 2、有没有发现，我们在ImageViewTarget的子类里面调用了我们自己编写的方法。
 * ImageViewTarget的包是com.bumptech.glide.request.target，我们的类包名是com.tlc.wanandroid.ui.utils，
 * 如果正常写代码，没有依赖，肯定是访问不到的，但是我们通过ASM却可以打破这个边界，只要在最后的APK里面，我们都可以访问到
 *
 *
 * @property className String?
 * @property glideHelperClass String
 * @constructor
 */
class GlideAdviceAdapter(
    api: Int,
    methodVisitor: MethodVisitor,
    access: Int,
    name: String?,
    descriptor: String?,
    private val className: String?
) : AdviceAdapter(api, methodVisitor, access, name, descriptor) {

    private val glideHelperClass = "com/tlc/wanandroid/ui/utils/GlideHelperKt"

    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        mv.visitVarInsn(ALOAD, 0)
        mv.visitVarInsn(ALOAD, 1)
        mv.visitMethodInsn(INVOKESTATIC,
            glideHelperClass,
            "detect",
            "(Lcom/bumptech/glide/request/target/ImageViewTarget;Ljava/lang/Object;)V",
            false)


    }
}