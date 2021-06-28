package com.shouguouo.algorithms.codewars.fivekyu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * @author shouguouo~
 * @date 2020/3/4 - 22:27
 */
@RunWith(Parameterized.class)
public class ParseMolecule {
    public static Map<String,Integer> getAtoms(String formula) {

        return new HashMap<String,Integer>();
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList( new Object[][] { {Arrays.asList("H", "O"),
            Arrays.asList( 2,   1 ),
            "H2O",
            "water"},

            {Arrays.asList("Mg", "H", "O"),
                Arrays.asList(  1,   2,   2 ),
                "Mg(OH)2",
                "magnesium hydroxide"},

            {Arrays.asList("K", "O", "N", "S"),
                Arrays.asList( 4,   14,  2,   4 ),
                "K4[ON(SO3)2]2",
                "Fremy's salt"},
        });
    }

    private Map<String,Integer> expected;
    private String formula, name;

    public ParseMolecule(List<String> atoms, List<Integer> nums, String formula, String name) {
        Map<String,Integer> exp = new HashMap<String,Integer>();
        for (int i = 0 ; i < atoms.size() ; i++) exp.put(atoms.get(i), nums.get(i));

        this.expected = exp;
        this.formula = formula;
        this.name = name;
    }

    @Test
    public void testMolecule() {
        System.out.println(expected);
        assertEquals(String.format("Should parse %s: %s", name, formula), expected, ParseMolecule.getAtoms(formula));
    }

    public static void main(String[] args) {
        boolean sb = Pattern.matches("[^\\u4E00-\\u9FA5\\uF900-\\uFA2D]+", "是的");
        System.out.println(sb);
        sb = Pattern.matches("[^\\u4E00-\\u9FA5\\uF900-\\uFA2D]+", "123");
        System.out.println(sb);

    }
}
