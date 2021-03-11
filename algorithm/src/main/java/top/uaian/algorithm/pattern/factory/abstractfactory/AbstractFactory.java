package top.uaian.algorithm.pattern.factory.abstractfactory;

public class AbstractFactory {
    public static void main(String[] args) {
        IFactory factory = new MeidiFactory();
        factory.produceIceBox().produce();
        factory.produceWasher().produce();
    }
}
