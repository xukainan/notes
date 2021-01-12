package top.uaian.algorithm.pattern.Singleton;

/**
 * description: 懒汉式单例模式<br>
 * date: 2021/1/11 17:40 <br>
 * author: xukainan <br>
 * version: 1.0 <br>
 */
public class LHan {

    private static LHan lHan; //静态属性，保证在静态方法中可用

    private LHan(){} //私有化构造方法,在外面不能new

    public static LHan getlHan(){ // 静态方法，可以直接使用类名.调用
        if(lHan == null){
            lHan = new LHan();
        }
        return lHan;
    }
}
