
public enum ResponseSensingModes {
    NONE(1), TRIGGERED(2), INHIBITED(3), TRACKED(4);

    private final int mode;

    private ResponseSensingModes(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
