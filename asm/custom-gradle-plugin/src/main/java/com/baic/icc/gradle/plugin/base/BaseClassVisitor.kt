package com.baic.icc.gradle.plugin.base

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*


open class BaseClassVisitor(api: Int, classVisitor: ClassVisitor): ClassVisitor(api, classVisitor) {

    protected var className: String? = null

    /**
     * 访问类的头部
     * 其中version指的是类的版本；
     * access指的是类的修饰符；
     * name类的名称；
     * signature类的签名，如果类不是泛型或者没有继承泛型类，那么signature为空；
     * superName类的父类名称；
     * @param version Int
     * @param access Int
     * @param name String
     * @param signature String
     * @param superName String
     * @param interfaces Array<out String>
     */
    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        className = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    /**
     * 访问类的注解
     * descriptor:表示类注解类的描述；
     * visible表示该注解是否运行时可见；
     * @param descriptor String
     * @param visible Boolean
     * @return AnnotationVisitor
     */
    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        return super.visitAnnotation(descriptor, visible)
    }

    /**
     * 访问一个类的域信息
     * access：表示该域的访问方式，public，private或者static,final等等；
     * name：指的是域的名称；
     * descriptor:域的描述,一般指的是该field的参数类型;
     * signature:指的是域的签名，一般是泛型域才会有签名;
     * value:指的该域的初始值
     * return FieldVisitor:表示将返回一个可以访问该域注解和属性的访问对象
     * @param access Int
     * @param name String
     * @param descriptor String
     * @param signature String
     * @param value Any
     * @return FieldVisitor
     */
    override fun visitField(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor {
        return super.visitField(access, name, descriptor, signature, value)
    }

    /**
     * 访问类的方法
     * @param access Int
     * @param name String
     * @param descriptor String
     * @param signature String
     * @param exceptions Array<out String>
     * @return MethodVisitor
     */
    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }

    /**
     * 访问类的尾部，只有当类访问结束时，才能调用该方法，同时必须调用该方法;
     */
    override fun visitEnd() {
        super.visitEnd()
    }

    /**
     * 是否是内部合成的
     * class, field, method, parameter, module
     * @param access Int
     * @return Boolean
     */
    protected fun isSynthetic(access: Int): Boolean {
        return (access and ACC_SYNTHETIC) != 0
    }

    /**
     * 是否是抽象的
     * class, method
     * @param access Int
     * @return Boolean
     */
    protected fun isAbstract(access: Int): Boolean {
        return (access and ACC_ABSTRACT) != 0
    }

    /**
     * 是否是native方法
     * method
     * @param access Int
     * @return Boolean
     */
    protected fun isNative(access: Int): Boolean {
        return (access and ACC_NATIVE) != 0
    }

    /**
     * 是否是接口
     * class
     * @param access Int
     * @return Boolean
     */
    protected fun isInterface(access: Int): Boolean {
        return (access and ACC_INTERFACE) != 0
    }

    protected fun log(logStr: String) {
        println("BaseClassVisitor - $logStr")
    }
}