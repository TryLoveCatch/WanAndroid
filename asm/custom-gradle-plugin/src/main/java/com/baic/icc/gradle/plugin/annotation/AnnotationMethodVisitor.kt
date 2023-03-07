package com.baic.icc.gradle.plugin.annotation

import org.objectweb.asm.MethodVisitor

class AnnotationMethodVisitor(api: Int, methodVisitor: MethodVisitor): MethodVisitor(api, methodVisitor) {
}