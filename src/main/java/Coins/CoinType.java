package Coins;

public enum CoinType {
    ONEPENCE(0.01, false),
    TWOPENCE(0.02, false),
    FIVEPENCE(0.05, true),
    TENPENCE(0.10, true),
    TWENTYPENCE(0.20, true),
    FIFTYPENCE(0.50, true),
    ONEPOUND(1.00, true),
    TWOPOUND(2.00, true);

    public final double value;
    public final boolean isValid;

    CoinType(double value, boolean isValid) {
        this.value = value;
        this.isValid = isValid;
    }
}
