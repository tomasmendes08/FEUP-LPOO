package MainPackage.data;

import MainPackage.gui.ElementView;
import MainPackage.gui.HeroView;

public class Hero extends Element {
    private HeroView draw = new HeroView();
    private int totalLifePoints;
    private int lifePoints;
    private int atk;
    private int def;
    private int level;
    private int neededExperience;
    private int experience;
    private int gold;

    public Hero(int x, int y) {
        super(x, y);
        gold = 0;
        level = 1;
        experience = 0;
        updateStats();
    }

    @Override
    public ElementView getDraw() {
        return draw;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public int getLevel() {
        return level;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public int getNeededExperience() {
        return neededExperience;
    }

    public int getTotalLifePoints() {
        return totalLifePoints;
    }

    public boolean setGold(int gold) {
        this.gold = gold;
        if (this.gold >= 450) {
            this.lifePoints = this.totalLifePoints;
            this.gold -= 450;
            return true;
        }
        return false;
    }

    private void updateStats () {
        totalLifePoints = (int) (10 + (level-1)*1.75);
        lifePoints = totalLifePoints;
        atk = 3 + (level-1) * 2;
        def = 3 + (level-1) * 2;
        neededExperience = (int) (5+ 0.5* Math.exp(0.5 *level));
    }

    public boolean takeDmg (int dmg) {
        lifePoints -= dmg;
        return lifePoints <= 0;
    }

    public boolean gainExp (int exp) {
        experience += exp;
        if (experience >= neededExperience) {
            experience -= neededExperience;
            level += 1;
            updateStats();
            return true;
        }
        return false;
    }
}
