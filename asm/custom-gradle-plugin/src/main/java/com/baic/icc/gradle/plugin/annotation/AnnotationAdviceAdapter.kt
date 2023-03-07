package com.baic.icc.gradle.plugin.annotation

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter

class AnnotationAdviceAdapter(
    api: Int,
    methodVisitor: MethodVisitor,
    access: Int,
    name: String?,
    descriptor: String?,
    private val className: String?
) : AdviceAdapter(api, methodVisitor, access, name, descriptor) {
    private var methodName: String?
    init {
        methodName = name
    }

    private var startIndex: Int = 0
    private var endIndex: Int = 0

    override fun onMethodEnter() {
        super.onMethodEnter()
        /**
         * 创建startTime局部变量
         *
         * var startTime = System.currentTimeMillis()
         */
        startIndex = newLocal(Type.LONG_TYPE)
        mv.visitMethodInsn(
            INVOKESTATIC,
            "java/lang/System",
            "currentTimeMillis",
            "()J",
            false
        )
        mv.visitVarInsn(LSTORE, startIndex)
    }

    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        /**
         * 创建endTime局部变量
         *
         * var endTime = System.currentTimeMillis()
         */
        endIndex = newLocal(Type.LONG_TYPE)
        mv.visitMethodInsn(
            INVOKESTATIC,
            "java/lang/System",
            "currentTimeMillis",
            "()J",
            false
        )
        mv.visitVarInsn(LSTORE, endIndex)

        /**
         * 创建StringBuilder
         *
         * var sb = StringBuilder()
         */
        // 使用Type.geteType("类路径") 会报错，需要改成Type.geteType("XXX.class“)的方式
//        val stringBuilderIndex = newLocal(Type.getType("Ljava/lang/StringBuilder"))
        val stringBuilderIndex = newLocal(Type.getType(StringBuilder::class.java))
        mv.visitTypeInsn(NEW, "java/lang/StringBuilder")
        // DUP，DUP的意思就是复制栈顶变量然后入栈，也就是说拷贝多一份this变量
        mv.visitInsn(DUP)
        mv.visitMethodInsn(
            INVOKESPECIAL,
            "java/lang/StringBuilder",
            "<init>",
            "()V",
            false
        )
        mv.visitVarInsn(ASTORE, stringBuilderIndex)


        /**
         * 调用StringBuilder的append方法
         * 参数类型是Ljava/lang/String;，也就是String
         * 参数值是：visitLdcInsn的参数
         */
        mv.visitVarInsn(ALOAD, stringBuilderIndex)
        // 把常量推到操作数栈
        mv.visitLdcInsn("$className#$methodName -> time:");
        mv.visitMethodInsn(
            INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "append",
            "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            false
        )
        mv.visitInsn(POP)

        /**
         * 调用StringBuilder的append方法
         * 参数类型是J，也就是 long类型
         */
        mv.visitVarInsn(ALOAD, stringBuilderIndex)
        mv.visitVarInsn(LLOAD, endIndex)
        mv.visitVarInsn(LLOAD, startIndex)
        // 栈顶的long类型相减，并且压入到栈顶
        mv.visitInsn(LSUB)
        mv.visitMethodInsn(
            INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "append",
            "(J)Ljava/lang/StringBuilder;",
            false
        )
        mv.visitInsn(POP)


        /**
         * 1、压入常量MethodCostTime
         * 2、调用StringBuilder.toString，并压入栈顶
         * 3、调用Log.d(String, String)
         *
         * 执行3的时候，参数就是1和2
         */
        mv.visitLdcInsn("MethodCostTime")
        mv.visitVarInsn(ALOAD, stringBuilderIndex)
        mv.visitMethodInsn(
            INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "toString",
            "()Ljava/lang/String;",
            false
        )
        mv.visitMethodInsn(
            INVOKESTATIC,
            "android/util/Log",
            "d",
            "(Ljava/lang/String;Ljava/lang/String;)I",
            false
        )
        mv.visitInsn(POP)

    }
}