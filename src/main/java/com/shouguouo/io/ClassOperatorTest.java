package com.shouguouo.io;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shouguouo~
 * @date 2020/8/31 - 19:18
 */
public class ClassOperatorTest {
    // Separators () {} [] ; , . ... @ ::
    // Operators = > < ! ~ ? : -> == >= <= != && || ++ --
    //           + - * / & | ^ % << >> >>>
    //           += -= *= /= &= |= ^= %= <<= >>= >>>=

    public static void main(String[] args) {
        System.out.println("\b");
        System.out.println("\t");
        System.out.println("\n");
        System.out.println("\f");
        System.out.println("\r");
        System.out.println("\"");
        // \u000A int a = 1;
        System.out.println("\7");
        System.out.println("\145");
        //System.out.println(a);

        String[] arr = {"12", "2312"};
        int[] ass = {1,23,4};
    }

    class A{

    }

    interface B{

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.PACKAGE,
        ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    @interface C{

    }

    interface D{

    }
    <@C T extends @C A & @C B & @C D> void sb(@C T a) {

    }

}