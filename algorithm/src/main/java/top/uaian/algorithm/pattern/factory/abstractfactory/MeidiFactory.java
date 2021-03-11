package top.uaian.algorithm.pattern.factory.abstractfactory;

public class MeidiFactory implements IFactory {

    @Override
    public IIceBox produceIceBox() {
        return new IceBoxFromMeidi();
    }

    @Override
    public IWasher produceWasher() {
        return new WasherFromMeidi();
    }
}
