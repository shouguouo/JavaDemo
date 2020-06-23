package com.swj.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wink~
 * @date 2020/5/21 - 9:14
 */
public class ReadFileForSort {
    public static void main(String[] args) throws IOException {
        //read("F:\\xbrl-platform-6.0\\bin\\xbrl-platform-6.0\\xbrl-schedulerclient\\1.log").forEach(System.out::println);
      /*  Map<String, String> map = read("C:\\Users\\shouwj25112\\Desktop\\111.txt").stream().collect(Collectors.toMap(x -> x.substring(0, x.indexOf("=") - 1).trim(), x -> x.substring(x.indexOf("=") + 1).trim(),
            (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u));}, LinkedHashMap::new));
        System.out.println(map.keySet().stream().collect(Collectors.joining(", \n", "(", ")")));
        System.out.println(map.values().stream().collect(Collectors.joining(", \n", "values (", ")")));
        */

        String str = new String("是多少".getBytes(), "utf-8");
        System.out.println(str);
    }

    public static List<String> read(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            lines = new BufferedReader(new FileReader(file)).lines().collect(Collectors.toList());
        }
        return lines;
    }

}
