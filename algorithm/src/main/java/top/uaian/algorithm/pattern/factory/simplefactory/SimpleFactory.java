package top.uaian.algorithm.pattern.factory.simplefactory;

public class SimpleFactory {
    public static void main(String[] args) {
        IProduct iProduct = produceProduct(0);
        iProduct.produce();
    }

    private static IProduct produceProduct(int productId){
        switch (productId){
            case ConstProduct.productA:
                return new ProductA();
            case ConstProduct.productB:
                return new ProductB();
            default:
                return null;
        }
    }
}

class ConstProduct{
    static final int productA = 0;
    static final int productB = 1;
}
