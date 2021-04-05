import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Koobas extends Varustus{
    private Random suvalineArv = new Random();

    public Random getSuvalineArv() {
        return suvalineArv;
    }

    public abstract void onRuumis();

    public abstract boolean isTegelaneRuumis();
}
