package top.uaian.algorithm.pattern.factory.factorymethod;

public class OnceRedPackageFactory implements IRedPackageFactory{
    @Override
    public void produce() {
        OnceRedPackage onceRedPackage = new OnceRedPackage();
        System.out.println(onceRedPackage.getDescription());
    }
}
