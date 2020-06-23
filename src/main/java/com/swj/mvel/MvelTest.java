package com.swj.mvel;

import com.google.common.collect.Lists;
import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.List;

/**
 * @author wink~
 * @date 2020/5/11 - 15:11
 */
public class MvelTest {
    public static void main(String[] args) {
        String expression = "mvelTest.test";
        Object ans = MVEL.eval(expression, new HashMap<String, Object>() {{
            put("mvelTest", new MvelTest());
        }});
        System.out.println(ans.getClass() +": " + ans);
    }

    public List<InnerData> test() {
        return Lists.newArrayList(new InnerData("寿炜剑"), new InnerData("项杭燕"));
    }

    public static class InnerData{
        private String name;

        public InnerData(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "InnerData{" +
                "name='" + name + '\'' +
                '}';
        }
    }
}
