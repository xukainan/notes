package top.uaian.algorithm.pattern.Singleton;

/**
 * description:  饿汉式单例模式<br>
 * date: 2021/1/11 17:43 <br>
 * author: xukainan <br>
 * version: 1.0 <br>
 */
public class EHan {

    private static EHan eHan = new EHan();

    private EHan(){}

    public static EHan getEHan(){
        return eHan;
    }
}
