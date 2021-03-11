package top.uaian.algorithm.pattern.factory.abstractfactory;

public class GeliFactory implements IFactory {

    @Override
    public IIceBox produceIceBox() {
        return new IceBoxFromGeli();
    }

    @Override
    public IWasher produceWasher() {
        return new WasherFromGeli();
    }
}
