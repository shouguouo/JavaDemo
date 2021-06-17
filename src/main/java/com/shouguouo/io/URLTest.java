package com.shouguouo.io;

/**
 * 功能说明:
 *
 * @author shouguouo~
 */
public class URLTest {
    public static void main(String[] args) throws Exception {
      /*  URL url = new URL("file", "", -1, "/C:/Users/wink~/Desktop/java/fundmanagement/libs/drs-fundmanagement.jar!/BOOT-INF/classes/template/FB102.xlsx");
        InputStream in = url.openStream();
        byte[] body = new byte[in.available()];
        System.out.println(in.available());
        in.read(body);
        System.out.println(Arrays.toString(body));
        in.close();*/
/*        File file = new File("C:/Users/wink~/Desktop/java/fundmanagement/libs/drs-fundmanagement.jar!/BOOT-INF/classes/template/FB109.xlsx");
        System.out.println(file.getAbsolutePath());
        file.createNewFile();*/
/*
        System.out.println(System.getProperty("user.dir"));
        File file = new File(".");
        System.out.println(file.getAbsolutePath());
        System.out.println( URLTest.class.getClassLoader().getResource(""));
        System.out.println( URLTest.class.getClassLoader().getResource("/"));
        System.out.println( URLTest.class.getResource(""));
        System.out.println( URLTest.class.getResource("/"));*/
/*
        File file = new File("F:\\DRS6.0\\branches\\DRS6.0-V20190816\\Patch\\DRS6.0-V20190816_SP3_01\\Sources\\drs-platform-6.0/fam-common/fam-datasource/src/main/java/com/hundsun/drs/datasource/enumeration");
        file.mkdirs();
*/

  /*      List<Test> sb = new ArrayList<>();
        sb.add(new Test(1));
        sb.add(new Test(4));
        sb.add(new Test(5));
        sb.add(new Test(2));
        sb.add(new Test(9));
        sb =  sb.stream().sorted(Comparator.comparingInt(Test :: getAge).reversed()).collect(Collectors.toList());
        System.out.println(Arrays.toString(sb.toArray()));
        Pattern p = Pattern.compile("^(\\d{4}\\d{1,2}\\d{1,2}\\[\\-?\\d+\\])(;\\d{4}\\d{1,2}\\d{1,2}\\[\\-?\\d+\\])*$");
        Matcher m = p.matcher("20191130[100]");
        if (m.matches()) {
            System.out.println("匹配上了");
        } else {
            System.out.println("木有");
        }
        System.out.println(Integer.MAX_VALUE);
        String sb = "002";
        String sb2 = "03";
        String sb3 = "003";
        System.out.println(sb.compareTo(sb2));
        System.out.println(sb.compareTo(sb3));
        for (int j = 0; j < 100; j ++) {
            List<String> source = new ArrayList<>();
            for (int i =1; i<=100; i++) {
                source.add("" + i);
            }
            List<String> target = new ArrayList<>();

            source.parallelStream().forEach(x -> target.add(x));
            System.out.println(target.size()==100);
        }*/
        Short sb = new Short("2333");
        System.out.println(sb == 2333);
    }
}
class Test{
    Integer age;

    public Test(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test{" +
            "age=" + age +
            '}';
    }
}
