package com.swj.collection;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;
/**
 * 流的练习题
 * Created by swj on 2018/1/23.
 */
public class StreamTest {
    /**
     * 计算流中所有的数的和
     * @param numbers
     * @return
     */
    public static int addUp(Stream<Integer> numbers){
        return numbers.reduce(0, (x, y) -> x+y);
    }

    /**
     * 双流合并
     * @param artists
     * @return
     */
    public static List<String> getArtistNamesAndNations(List<Artist> artists){
        return artists.stream().flatMap(artist -> Stream.of(artist.getArtistName(),artist.getAddress())).collect(toList());
    }

    /**
     * 计算字符串中小写字符的个数
     * @param str
     */
    public static long getLowerCaseCount(String str){
        return str.chars().filter(Character::isLowerCase).count();
    }

    /**
     * 返回小写字母个数最大的字符串
     * @param str
     * @return
     */
    public static Optional<String> getLowerCaseString(String[] str){
        return Stream.of(str).max(Comparator.comparing(x ->getLowerCaseCount(x)));
    }
    public static OptionalLong mostLowcaseLetters(String[] str){
        return Stream.of(str).mapToLong(x -> getLowerCaseCount(x)).max();
    }

    public static void main(String[] args) {
        System.out.println(addUp(Stream.of(1,2)));
        ArrayList<Artist> allArtists = new ArrayList<>();
        allArtists.add(new Artist("London","swj"));allArtists.add(new Artist("Beijing","cyb"));
        allArtists.add(new Artist("Hangzhou","xhy"));allArtists.add(new Artist("London","tyq"));
        allArtists.add(new Artist("Shanghai","tpw"));allArtists.add(new Artist("Hangzhou","skl"));
        System.out.println(getArtistNamesAndNations(allArtists));
        System.out.println(getLowerCaseCount("dsJISjoadsASd"));
        System.out.println(getLowerCaseString(new String[]{"dsSdsSsdf", "dsSWIWI", "asdad"}));
        System.out.println(mostLowcaseLetters(new String[]{"dsSdsSsdf", "dsSWIWI", "asdad"}));
    }
}
