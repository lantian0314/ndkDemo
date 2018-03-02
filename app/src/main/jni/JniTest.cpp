//
// Created by xingyatong on 2018/3/2.
//

#include "JniTest.h"

JNIEXPORT jstring JNICALL Java_orm_ndkdemo_JniTest_getCString
        (JNIEnv *env, jclass clazz){
    return env->NewStringUTF("This is Jni test!!!");
}

JNIEXPORT jint JNICALL Java_orm_ndkdemo_JniTest_calAAndB
        (JNIEnv *env, jclass clazz, jint a, jint b){
    return (a+b);
}
