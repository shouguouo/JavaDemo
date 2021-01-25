package com.swj.io;

import com.google.common.collect.Maps;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shouguouo~
 * @date 2021/1/25 - 10:08
 */
public class LineCounter {
    public static void main(String[] args) {
        File file = new File("D:\\doc");
        Map<String, Integer> linesMap = Maps.newHashMap();
        try {
            recurFiles(file, linesMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        linesMap = linesMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
        linesMap.entrySet().forEach(System.out::println);
    }

    private static void recurFiles(File file, Map<String, Integer> linesMap) throws IOException {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File single : files) {
                recurFiles(single, linesMap);
            }
        } else {
            if (file.canRead()) {
                LineNumberReader lineReader = new LineNumberReader(new FileReader(file));
                lineReader.skip(Long.MAX_VALUE);
                linesMap.put(file.toString(), lineReader.getLineNumber() + 1);
            }
        }
    }
}
