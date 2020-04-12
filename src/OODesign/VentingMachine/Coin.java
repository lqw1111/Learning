package OODesign.VentingMachine;

public enum Coin {
    PENNY(1f),
    NICKLE(5),
    DIME(10),
    QUARTER(25);

    private float value;

    Coin(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
