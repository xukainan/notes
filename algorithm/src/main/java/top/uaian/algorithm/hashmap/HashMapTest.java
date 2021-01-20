package top.uaian.algorithm.hashmap;

/**
 * description:  <br>
 * date: 2021/1/20 16:35 <br>
 * author: xukainan <br>
 * version: 1.0 <br>
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        map.put("谭一成","我大儿");
        map.put("陈丕江","我小儿");
        map.put("徐锴男","爸爸");
        System.out.println(map.get("徐锴男"));
        System.out.println(map.get("陈丕江"));
        System.out.println(map.get("谭一成"));
    }
}
