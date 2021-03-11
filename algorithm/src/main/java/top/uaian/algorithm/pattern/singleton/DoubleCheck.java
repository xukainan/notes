package top.uaian.algorithm.pattern.singleton;

/**
 * description:  <br>
 * date: 2021/1/12 9:44 <br>
 * author: xukainan <br>
 * version: 1.0 <br>
 */
public class DoubleCheck {
    private static volatile DoubleCheck doubleCheck;
    private DoubleCheck(){}
    public static DoubleCheck getDoubleCheck(){
        if (doubleCheck == null){
            synchronized (DoubleCheck.class){
                if(doubleCheck == null){
                    doubleCheck = new DoubleCheck();
                }
            }
        }
        return doubleCheck;
    }
}
