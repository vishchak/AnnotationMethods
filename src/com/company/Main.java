package com.company;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        Class<?> cls = Multiply.class;
        try {
            Method method = cls.getMethod("test", int.class, int.class);
            Method[] methods = cls.getDeclaredMethods();
            for (Method m :
                    methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test var = method.getAnnotation(Test.class);
                    method.invoke("null", var.a(), var.b());
                }
            }

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}


@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Test {
    int a();

    int b();
}

class Multiply {
    @Test(a = 2, b = 5)
    public static void test(int a, int b) {
        System.out.println(a * b);
    }
}
