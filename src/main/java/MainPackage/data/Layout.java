package MainPackage.data;

import java.util.ArrayList;
import java.util.List;

public abstract class Layout {
    int height;
    int width;
    List<Walls> walls = new ArrayList<>();

    protected abstract void createWalls();
    public abstract boolean isInside(Position pos);

    public List<Walls> getWalls() {
        return walls;
    };
}
