package OODesign.Furniture;

public class Wood extends Material {
    @Override
    public boolean firePreventation() {
        return false;
    }

    @Override
    public float pressTest() {
        return 100;
    }
}
