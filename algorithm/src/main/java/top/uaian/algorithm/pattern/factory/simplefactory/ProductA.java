package top.uaian.algorithm.pattern.factory.simplefactory;

public class ProductA implements IProduct{
    @Override
    public void produce() {
        System.out.println("produce product A...");
    }
}
