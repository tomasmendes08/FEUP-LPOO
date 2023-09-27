package MainPackage.data;

import MainPackage.gui.ElementView;
import MainPackage.gui.WallsView;

public class Walls extends Element {
    private WallsView draw = new WallsView();

    Walls(int x, int y){
        super(x, y);
    }

    @Override
    public ElementView getDraw() {
        return draw;
    }
}
