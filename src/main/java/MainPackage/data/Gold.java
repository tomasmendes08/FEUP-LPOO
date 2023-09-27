package MainPackage.data;

import MainPackage.gui.ElementView;
import MainPackage.gui.GoldView;
import java.util.Random;

public class Gold extends Element {
    private GoldView draw = new GoldView();
    private int value;

    Gold(int x, int y){
        super(x, y);
        initValue();
    }

    @Override
    public ElementView getDraw() {
        return draw;
    }

    private void initValue() {
        Random rand = new Random();
        value = rand.nextInt(50) +1;
    }

    public int getValue() {
        return value;
    }
}
