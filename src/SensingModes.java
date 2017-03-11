
public enum SensingModes {
    NONE(1), ATRIUM(2), VENTRICAL(3), DUAL(4);

    private final int mode;

    private SensingModes(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
