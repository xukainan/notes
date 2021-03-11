package top.uaian.algorithm.pattern.factory.factorymethod;

public class DelayRedPackageFactory implements IRedPackageFactory{
    @Override
    public void produce() {
        DelayRedPackage delayRedPackage = new DelayRedPackage();
        System.out.println(delayRedPackage.getDescription());
    }
}
