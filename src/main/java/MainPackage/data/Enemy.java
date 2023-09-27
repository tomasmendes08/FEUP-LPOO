package MainPackage.data;

import MainPackage.gui.ElementView;
import MainPackage.gui.EnemyView;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Element {
    private EnemyView draw = new EnemyView();
    private List<Position> adjacent;
    private int HP;

    public Enemy (int x, int y, int hp) {
        super(x, y);
        HP = hp;
        initAdj();
    }

    @Override
    public ElementView getDraw() {
        return draw;
    }

    private void initAdj() {
        adjacent = new ArrayList<>();

        adjacent.add(new Position(position.getX()-1, position.getY()));
        adjacent.add(new Position(position.getX()+1, position.getY()));
        adjacent.add(new Position(position.getX(), position.getY()-1));
        adjacent.add(new Position(position.getX(), position.getY()+1));
    }

    public boolean isAdjacent(Position p) {
        for (Position pos : adjacent) {
            if (p.equals(pos)) return true;
        }
        return false;
    }

    public boolean takeDmg (int dmg) {
        HP -= dmg;
        return HP <= 0;
    }

    public int getHP() {
        return HP;
    }
}
