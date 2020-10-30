package com.xiaoooyu.demo.trace

/**
 * Copyright (c) 2020 Teambition All Rights Reserved.
 */
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class DebugTrace