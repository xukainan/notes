package top.uaian.algorithm.pattern.factory.factorymethod;

public class RandomRedPackageFactory implements IRedPackageFactory{
    @Override
    public void produce() {
        RandomRedpackage randomRedpackage = new RandomRedpackage();
        System.out.println(randomRedpackage.getDescription());
    }
}
