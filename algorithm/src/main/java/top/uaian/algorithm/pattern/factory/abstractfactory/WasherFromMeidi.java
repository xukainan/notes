package top.uaian.algorithm.pattern.factory.abstractfactory;

public class WasherFromMeidi implements IWasher{
    @Override
    public void produce() {
        System.out.println("i am a WasherFromMeidi");
    }
}
