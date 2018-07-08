package AlKharidSmelter;

public class Ore {
    public int id;
    public int amount;

    public Ore(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}
