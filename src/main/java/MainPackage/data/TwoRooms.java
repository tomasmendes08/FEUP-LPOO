package MainPackage.data;

import java.util.Random;

public class TwoRooms extends Layout {


    TwoRooms (int h, int w) {
        height = ((h-4) /2);
        width = (w/2)-2;
        createWalls();
    }

    @Override
    protected void createWalls() {
        Random r =  new Random();
        int exitLeft = r.nextInt(width-3) +1;
        drawLeftRoom(exitLeft);
        int exitRight = r.nextInt(height-3) +1;
        drawRightRoom(exitRight);
        drawCorridor(exitLeft, exitRight);
    }

    private void drawLeftRoom(int exit) {
        Walls w;
        for (int i = 0; i < width; i++) {
            w = new Walls(i, 0);
            walls.add(w);
            if (i == exit) continue;
            w = new Walls(i, height-1);
            walls.add(w);
        }
        for (int j = 1; j < height-1; j++) {
            w = new Walls(0, j);
            walls.add(w);
            w = new Walls(width-1, j);
            walls.add(w);
        }
    }

    private void drawRightRoom (int exit) {
        Walls w;
        for (int i = 0; i < width; i++) {
            w = new Walls(width +4 +i, height +1);
            walls.add(w);
            w = new Walls(width +4 +i, 2*height-1);
            walls.add(w);
        }
        for (int j = 1; j < height-1; j++) {
            w = new Walls(2*width +3, height +1 +j);
            walls.add(w);
            if (j == exit) continue;
            w = new Walls(width +4, height+1 +j);
            walls.add(w);
        }
    }

    private void drawCorridor(int exitL, int exitR) {
        Walls w;
        for (int i = height; i < height +1 +exitR; i++) {
            w = new Walls(exitL -1, i);
            walls.add(w);
            w = new Walls(exitL +1, i);
            walls.add(w);
        }
        for (int j = exitL +2; j < width +4; j++) {
            w = new Walls(j, height +1 +exitR -1);
            walls.add(w);
            w = new Walls(j, height +1 +exitR +1);
            walls.add(w);
        }
        walls.add(new Walls(exitL -1, height +1 +exitR));
        walls.add(new Walls(exitL -1, height +1 +exitR +1));
        walls.add(new Walls(exitL, height +1 +exitR +1));
        walls.add(new Walls(exitL +1, height +1 +exitR +1));
    }

    @Override
    public boolean isInside(Position pos) {
        boolean left = pos.getX() > 0 && pos.getX() < width-1 && pos.getY() > 0 && pos.getY() < height-1;
        boolean right = pos.getX() > width +4 && pos.getX() < 2*width +3 && pos.getY() > height +1 && pos.getY() < 2*height-1;
        return left || right;
    }
}
