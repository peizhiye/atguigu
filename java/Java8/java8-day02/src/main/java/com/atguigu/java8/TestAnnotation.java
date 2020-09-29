package com.atguigu.java8;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 */
public class TestAnnotation {

    // checker framework
    private /*@NonNull*/ Object obj = null;

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation("abc") String str) {

    }

    @Test
    public void test() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method method = clazz.getMethod("show");
        MyAnnotation[] mas = method.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation myAnnotation : mas) {
            System.out.println(myAnnotation.value());
        }

    }
}
