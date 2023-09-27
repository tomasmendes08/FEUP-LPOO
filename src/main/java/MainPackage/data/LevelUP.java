package MainPackage.data;

import MainPackage.gui.ElementView;
import MainPackage.gui.LevelUPView;

public class LevelUP extends Element {
    private LevelUPView draw = new LevelUPView();

    LevelUP(int x, int y) {
        super(x, y);
    }

    @Override
    public ElementView getDraw() {
        return draw;
    }
}
