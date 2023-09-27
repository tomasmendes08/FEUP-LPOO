package MainPackage.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArenaModel {
    private int width, height;
    private Layout layout;
    private Hero hero;
    private List<Gold> gold;
    private List<Enemy> enemies;
    private LevelUP levelUP;
    private int lvl;

    public ArenaModel(int width, int height){
        this.width = width;
        this.height = height;
        this.layout = new OneRoom(height, width);
        this.hero = createHero();
        this.lvl = 0;
        newLevel();
    }

    public Hero createHero() {
        Random random = new Random();
        Position pos;
        do {
            pos = new Position(random.nextInt(width-1)+1, random.nextInt(height-5) +1);
        } while (!isInArena(pos));
        return new Hero(pos.getX(), pos.getY());
    }

    private void placeHero() {
        Random random = new Random();
        Position pos;
        do {
            pos = new Position(random.nextInt(width-1)+1, random.nextInt(height-5) +1);
        } while (!isInArena(pos));
        hero.setPosition(pos);
    }

    public List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        Random random = new Random();

        Position position;

        for(int i = 0; i < random.nextInt(3) +1; i++){
            position = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 7) + 1);
            if(position.equals(hero.getPosition()) || !isInArena(position))
                i--;
            else
                enemies.add(new Enemy(position.getX(), position.getY(),3 + 2*(lvl-1)));
        }

        return enemies;
    }

    public int pickGold(){
        int result = 0;
        for(int i = 0; i < gold.size(); i++){
            if(gold.get(i).getPosition().equals(hero.getPosition())){
                result = gold.get(i).getValue();
                if(hero.setGold(hero.getGold() + result)) result = -result;
                gold.remove(i);
                return result;
            }
        }
        return result;
    }

    public List<Gold> createGold(){
        List<Gold> gold = new ArrayList<>();
        Random random = new Random();

        Position position;

        for(int i = 0; i < 2; i++){
            position = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 7) + 1);
            if(position.equals(hero.getPosition()) || !isInArena(position))
                i--;
            else
                gold.add(new Gold(position.getX(), position.getY()));
        }

        return gold;
    }

    public LevelUP createLevelUP() {
        Random random = new Random();
        Position pos;
        do {
            pos = new Position(random.nextInt(width-1)+1, random.nextInt(height-5) +1);
        } while (!isInArena(pos) || hero.getPosition().equals(pos));
        return new LevelUP(pos.getX(), pos.getY());
    }

    public void newLevel() {
        lvl++;
        if(lvl %2 == 0) this.layout = new TwoRooms(height, width);
        else this.layout = new OneRoom(height, width);
        this.placeHero();
        this.gold = createGold();
        this.enemies = createEnemies();
        this.levelUP = createLevelUP();
    }

    public boolean isInArena(Position pos) {
        return layout.isInside(pos);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Layout getLayout() { return layout; }

    public Hero getHero() {
        return hero;
    }

    public List<Gold> getGold() {
        return gold;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public LevelUP getLevelUP() { return levelUP; }

    public int getLevel() {
        return lvl;
    }
}
