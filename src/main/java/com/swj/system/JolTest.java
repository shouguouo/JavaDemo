package com.swj.system;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wink~
 * @date 2020/7/15 - 9:07
 */
public class JolTest {
    /**
     * 对象头：12字节 * 8 = 96位
     * 实例数据
     * 对齐填充：补充8位
     * out put：
     * com.swj.system.JolTest object internals:
     *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     *       0    12        (object header)                           N/A
     *      12     4        (loss due to the next object alignment)
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     */
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(JolTest.class).toPrintable());
    }
}
