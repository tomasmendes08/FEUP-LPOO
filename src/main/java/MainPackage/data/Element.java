package MainPackage.data;

import MainPackage.gui.ElementView;

public abstract class Element {
    protected Position position;

    Element(int x, int y){
        this.position = new Position(x, y);
    }

    public abstract ElementView getDraw();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
