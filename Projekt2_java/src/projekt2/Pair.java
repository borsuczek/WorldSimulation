package projekt2;

public class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int first() {
        return x;
    }

    public int second() {
        return y;
    }
}
