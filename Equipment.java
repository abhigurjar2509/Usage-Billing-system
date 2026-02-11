public class Equipment {
    int id;
    String name;
    double ratePerHour;
    boolean inUse;

    public Equipment(int id, String name, double ratePerHour) {
        this.id = id;
        this.name = name;
        this.ratePerHour = ratePerHour;
        this.inUse = false;
    }
}
