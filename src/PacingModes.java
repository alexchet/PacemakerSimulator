
public enum PacingModes {
    NONE(1), ATRIUM(2), VENTRICAL(3), DUAL(4);

    private final int mode;

    private PacingModes(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
