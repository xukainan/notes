package top.uaian.algorithm.pattern.singleton;

/**
 * description:  <br>
 * date: 2021/1/12 10:32 <br>
 * author: xukainan <br>
 * version: 1.0 <br>
 */
public class StaticClass {
    private StaticClass(){}

    private static class StaticClassFactory{
         private static StaticClass staticClass = new StaticClass();
    }

    public static StaticClass getStaticClass(){
        return StaticClassFactory.staticClass;
    }
}
