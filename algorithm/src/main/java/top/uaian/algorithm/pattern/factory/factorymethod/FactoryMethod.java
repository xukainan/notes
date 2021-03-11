package top.uaian.algorithm.pattern.factory.factorymethod;

public class FactoryMethod {
    public static void main(String[] args) {
        IRedPackageFactory factory = new RandomRedPackageFactory();
        factory.produce();
    }
}
