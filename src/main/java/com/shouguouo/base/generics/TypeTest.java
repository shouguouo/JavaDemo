package com.shouguouo.base.generics;

import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.io.Serializable;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shouguouo~
 * @date 2020/3/30 - 19:18
 */
public class TypeTest<T, V extends @Custom Number & Serializable> { // T,V 为type variable

    private Number number;

    public T t;

    public V v;

    public List<T> list = new ArrayList<>(); // List<T> 为parameterized type

    public Map<String, T> map = new HashMap<>(); // Map<String, T> 为parameterized type

    public T[] tArray;

    public List<T>[] ltArray;

    public TypeTest testClass;

    public TypeTest<T, Integer> testClass2;

    public Map<? super String, ? extends Number> mapWithWildcard;


    public <X extends Number> TypeTest(X x, T t) {
        this.number = x;
        this.t = t;
    }

    public <Y extends T> void method(Y y) {
        t = y;
    }


    static <String, T, Ali> String get(String string, Ali ali) {
        return string;
    }

    public static void main(String[] args) {
        try {
            Field v = TypeTest.class.getField("v");
            Type type = v.getGenericType();
            System.out.println(type);
            System.out.println(type.getTypeName());
            TypeVariableImpl typeVariable = (TypeVariableImpl) type;
            System.out.println(typeVariable);
            System.out.println(Arrays.asList(typeVariable.getBounds()));
            System.out.println(typeVariable.getGenericDeclaration());
            System.out.println(typeVariable.getName());
            AnnotatedType[] annotatedTypes = typeVariable.getAnnotatedBounds();
            System.out.println(Arrays.asList(annotatedTypes));
            System.out.println(annotatedTypes[0].getAnnotations()[0]);


            System.out.println("------------------------------------------------");

            Field list = TypeTest.class.getField("list");
            Type genericType1 = list.getGenericType();
            System.out.println(genericType1.getTypeName());

            Field map = TypeTest.class.getField("map");
            Type genericType2 = map.getGenericType();
            System.out.println(genericType2.getTypeName());
            if (genericType2 instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType)genericType2;
                Type[] types = pType.getActualTypeArguments();
                System.out.println(Arrays.asList(types));
                System.out.println(pType.getRawType());
                System.out.println(pType.getOwnerType());
            }

            Field tArray = TypeTest.class.getField("tArray");
            System.out.println(tArray.getGenericType());
            Field ltArray = TypeTest.class.getField("ltArray");
            System.out.println(ltArray.getGenericType());
            if (tArray.getGenericType() instanceof GenericArrayType) {
                GenericArrayType arrayType = (GenericArrayType) tArray.getGenericType();
                System.out.println(arrayType.getGenericComponentType());
            }


            Field mapWithWildcard = TypeTest.class.getField("mapWithWildcard");
            Type wild = mapWithWildcard.getGenericType();
            if (wild instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) wild;
                Type[] actualTypes = pType.getActualTypeArguments();
                System.out.println("WildcardType1:" + Arrays.asList(actualTypes));
                WildcardType first = (WildcardType) actualTypes[0];
                WildcardType second = (WildcardType) actualTypes[1];
                System.out.println("WildcardType2: lower:" + Arrays.asList(first.getLowerBounds()) + "  upper:" + Arrays.asList(first.getUpperBounds()));
                System.out.println("WildcardType3: lower:" + Arrays.asList(second.getLowerBounds()) + "  upper:" + Arrays.asList(second.getUpperBounds()));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        Integer first = 222;
        Long second = 333L;
        Integer result = get(first, second);
        System.out.println(result);
    }
}
