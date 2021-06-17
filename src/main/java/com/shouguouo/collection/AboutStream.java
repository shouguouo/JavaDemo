package com.shouguouo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流（不可逆）与集合 JDK1.8
 * Created by swj on 2018/1/22.
 */
public class AboutStream {
    /**
     * 关于惰性求值和及早求值
     * 惰性求值：比如筛选 没有产生实际的数值
     * 及早求值：比如筛选出的人数 产生了实际的值
     * 几乎所有的链式操作都是进行各种惰性求值的链式操作最后加上一个及早求值的方法返回想要的结果
     */
    /**
     *可以尝试用建造者的设计模式去了解Stream
     * @param args None
     */
    public static void main(String[] args) {
        ArrayList<Artist> allArtists = new ArrayList<>();
        allArtists.add(new Artist("London","swj"));allArtists.add(new Artist("Beijing","cyb"));
        allArtists.add(new Artist("Hangzhou","xhy"));allArtists.add(new Artist("London","tyq"));
        allArtists.add(new Artist("Shanghai","tpw"));allArtists.add(new Artist("Hangzhou","skl"));
        //正常for循环计算来自London的艺术家的人数
        /**
         * 缺点：每次迭代集合类代码量大 改成并行不方便 阅读性差 将行为和方法混为一谈
         */
        int count = 0;
        for (Artist artist: allArtists) {
            if (artist.isFrom("London"))
                count++;
        }
        System.out.println(count);

        /**
         * 上述使用迭代器的方式(for循环) 称做外部迭代
         * 内部迭代：通过集合本身内部的流进行处理 将stream（）改为parallelStream（）就是并行操作
         */
        long total = allArtists.stream().filter(artist -> artist.isFrom("London")).count();
        System.out.println(total);
        /**
         * 对于数组 Arrays类提供静态函数stream（）获取数组流对象
         */
        String[] str = new String[]{"ds", "dsa", "aa"};
        Stream<String> arr = Arrays.stream(str);
        Stream<String> stream = Stream.of("ss", "aaa", "www");

        //通过collect(Collects.toList())方法将流变成集合
        List<String> list = stream.collect(Collectors.toList());

        //去重distinct()
        //截取limit(N):截取流的前N个元素
        //skip(N):跳过流的前N个元素
        //映射Map 新的值代替stream中的值
        List<String> artistNames = allArtists.stream().filter(artist -> artist.isFrom("London")).map(artist1 -> artist1.getArtistName()).collect(Collectors.toList());
        System.out.println(artistNames.get(1));

        //flatMap 将多个流合并
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
        together.forEach(n -> System.out.println(n));

        //最大最小值 Comparator.comparing()
        Integer max = together.stream().max(Comparator.comparing(n -> n)).get();
        System.out.println(max);

        //归约reduce 从一组值生成一个值 count max min 本质都是reduce操作 只是被纳入标准库了
        //reduce的第一个参数是初始值 第二个是需要进行的归约操作
        int sum = Stream.of(1, 2, 3).reduce(1, (acc, element) -> acc + element);
        System.out.println(sum);

        /**
         * Optional对象 只存一个或0个元素 用于防止出现NPE（NullPointException）
         * isPresent()判断容器中是否有空值
         * ifPresent(Consume lambda) 容器若不为空 则执行括号中的lambda表达式
         * T get() 获取容器中的元素 为空则抛出NoSuchElement异常
         * T orElse(T other) 获取容器中的元素 若容器为空则返回括号中的默认值
         */
    }
}
class Artist{
    private String address;
    private String name;

    Artist(String address, String name){
        this.address = address;
        this.name = name;
    }
    public boolean isFrom(String address){
        return this.address.equals(address);
    }
    public String getArtistName(){
        return name;
    }
    public String getAddress(){ return address;}
}
