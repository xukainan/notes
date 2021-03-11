package top.uaian.algorithm.pattern.factory.abstractfactory;

public class WasherFromGeli implements IWasher{
    @Override
    public void produce() {
        System.out.println("i am a WasherFromGeli");
    }
}
