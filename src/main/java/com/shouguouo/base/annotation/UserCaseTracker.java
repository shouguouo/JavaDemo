package com.shouguouo.base.annotation;

import com.google.common.collect.Lists;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author shouguouo~
 * @date 2020/7/6 - 16:54
 */
public class UserCaseTracker {
    public static void main(String[] args) {
        trackUserCase(Lists.newArrayList(46, 47, 48, 49, 50), PasswordUtils.class);
    }

    public static void trackUserCase(List<Integer> idList, Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            UserCase uc = method.getAnnotation(UserCase.class);
            if (uc != null) {
                if (idList.contains(uc.id())) {
                    System.out.println("Found id：" + uc.id() + "{" + uc.description() + "}");
                    idList.remove(Integer.valueOf(uc.id()));
                }
            }
        }
        for (Integer notContained : idList) {
            System.out.println("Warning: missing id " + notContained);
        }

    }
}
class PasswordUtils {

    @UserCase(id = 47, description = "validate password")
    public boolean validatePassword(String pwd) {
        return pwd.matches("\\w*\\d\\w*");
    }

    @UserCase(id = 48)
    public String encrypt(String pwd) {
        return new StringBuilder(pwd).reverse().toString();
    }

    @UserCase(id = 49, description = "check for new password")
    public boolean checkForNewPassword(List<String> prevPwdList, String pwd) {
        return !prevPwdList.contains(pwd);
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface UserCase {
    int id();
    String description() default "description doesn't define";
}
