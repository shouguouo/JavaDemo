package com.shouguouo.io;

import com.google.common.collect.Lists;
import com.shouguouo.utils.PatternUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shouguouo~
 * @date 2020/4/10 - 9:41
 */
public class FindPrefixURL {

    public static List<String> contract = Lists.newArrayList();

    public static void main(String[] args) {
        File file = new File("F:");
        Map<String, List<String>> resMap = new HashMap<>();
        try {
            recurFindFile(file, resMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resMap.size());
        // contract.stream().filter(x -> resMap.get(x) == null).forEach(System.out::println);
        resMap.forEach((key, value) -> {

        });
        contract.forEach(x -> {
            System.out.println("---------------");
            System.out.println(x);
            if (resMap.get(x) != null) {
                System.out.println(resMap.get(x).size());
                resMap.get(x).forEach(System.out::println);
            }

        });
    }

    public static void recurFindFile(File file, Map<String, List<String>> resMap) throws IOException {
        if (file.exists()) {
            if (file.isFile()) {
                if (contract.contains(file.getName())) {
                    checkUrl(file, resMap);
                }
            } else {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    for (File subFile : subFiles) {
                        recurFindFile(subFile, resMap);
                    }
                }

            }
        }
    }
    public static void checkUrl(File file, Map<String, List<String>> resMap) throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line);
            line = bufferedReader.readLine();
        }
        List<String> res = PatternUtil.getMatchStringList(sb.toString(), "([\"|']/drsOld/.*?[\"|'])");
        //res.addAll(PatternUtil.getMatchStringList(sb.toString(), "([\"|']/drs/.*?[\"|'])"));
        //res.addAll(PatternUtil.getMatchStringList(sb.toString(), "([\"|']/xbrl/.*?[\"|'])"));
        resMap.put(file.getName().replace(".vue", ""), res);
    }
}
