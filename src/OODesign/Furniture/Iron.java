package OODesign.Furniture;

public class Iron extends Material {
    @Override
    public boolean firePreventation() {
        return true;
    }

    @Override
    public float pressTest() {
        return 1000;
    }
}
