package MainPackage.data;

public class OneRoom extends Layout {

    OneRoom(int h, int w) {
        height = h;
        width = w;
        createWalls();
    }

    @Override
    protected void createWalls() {
        Walls w;
        for (int i = 0; i < width; i++) {
            w = new Walls(i, 0);
            walls.add(w);
            w = new Walls(i, height-5);
            walls.add(w);
        }
        for (int j = 1; j < height-5; j++) {
            w = new Walls(0, j);
            walls.add(w);
            w = new Walls(width-1, j);
            walls.add(w);
        }
    }

    @Override
    public boolean isInside(Position pos) {
        return pos.getX() > 0 && pos.getX() < width-1 && pos.getY() > 0 && pos.getY() < height-6;
    }
}
