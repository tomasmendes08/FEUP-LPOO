package MainPackage.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import MainPackage.controller.ArenaController;
import MainPackage.data.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaView {
    private TextGraphics graphics;
    private int screenHeight;
    private String info1;
    private String info2;
    private String info3;
    private Map<ACTION, String> infos;

    public enum COMMAND {UP, RIGHT, DOWN, LEFT, STRIKE, PAUSE, ENTER, GAMEOVER};
    public enum ACTION {PICKUP, ATTACK, DEFEND, LEVELUP, GAIN, PROGRESS, HEALTH}

    public ArenaView(int height, Screen screen) {
        this.screenHeight = height;

        this.graphics = screen.newTextGraphics();

        this.info1 = "";
        this.info2 = "";
        this.info3 = "";
        this.infos = buildInfoMap();
    }

    private Map<ACTION, String> buildInfoMap() {
        Map<ACTION, String> map = new HashMap<>();
        map.put(ACTION.PICKUP, "Picked up %d gold.");
        map.put(ACTION.ATTACK, "Did %d damage to the enemy!");
        map.put(ACTION.DEFEND, "Took %d damage!");
        map.put(ACTION.GAIN, "Gained %d experience points!");
        map.put(ACTION.LEVELUP, "Leveled up! You are now at level %d!");
        map.put(ACTION.PROGRESS, "You went deeper inside! You are now at level B%d!");
        map.put(ACTION.HEALTH, "You spent %d gold to gain back all your health!");
        return map;
    }

    public void drawArena(ArenaModel arenaModel) throws IOException{
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(arenaModel.getWidth(), arenaModel.getHeight()), ' ');

        drawLayout(arenaModel.getLayout());
        drawLevelUP(arenaModel.getLevelUP());
        drawHeroAndStats(arenaModel.getHero());
        drawGold(arenaModel.getGold());
        drawEnemies(arenaModel.getEnemies());

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.disableModifiers();
        graphics.putString(0, screenHeight -3, info1);
        graphics.putString(0, screenHeight -2, info2);
        graphics.putString(0, screenHeight -1, info3);
    }

    public void drawLayout(Layout l) {
        for (Walls w : l.getWalls()) {
            w.getDraw().draw(graphics, w.getPosition());
        }
    }

    public void drawLevelUP(LevelUP levelUP) {
        levelUP.getDraw().draw(graphics, levelUP.getPosition());
    }

    public void drawHeroAndStats(Hero hero) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        hero.getDraw().draw(graphics, hero.getPosition());
        graphics.putString(0, screenHeight - 4, "LIFE: " + hero.getLifePoints() + "/" + hero.getTotalLifePoints() + " ATK: " + hero.getAtk() + " DEF: " + hero.getDef() + " GOLD: " + hero.getGold() + " LEVEL: " + hero.getLevel() + " EXP: " + hero.getExperience() + "/" + hero.getNeededExperience());
    }

    public void drawGold(List<Gold> gold) {
        for (Gold g : gold) {
            g.getDraw().draw(graphics, g.getPosition());
        }
    }

    public void drawEnemies(List<Enemy> enemies) {
        for (Enemy e : enemies) {
            e.getDraw().draw(graphics, e.getPosition());
        }
    }


    public COMMAND getCommand(ArenaController arena) throws IOException {
        while(true){
            KeyStroke key = arena.getScreen().readInput();

            if(key.getKeyType() == KeyType.ArrowUp) {
                return COMMAND.UP;
            }
            if(key.getKeyType() == KeyType.ArrowDown){
                return COMMAND.DOWN;
            }
            if(key.getKeyType() == KeyType.ArrowRight){
                return COMMAND.RIGHT;
            }
            if(key.getKeyType() == KeyType.ArrowLeft){
                return COMMAND.LEFT;
            }
            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'a'){
                return COMMAND.STRIKE;
            }
            if(key.getKeyType() == KeyType.Escape){
                return COMMAND.PAUSE;
            }
            if(key.getKeyType() == KeyType.Enter){
                return COMMAND.ENTER;
            }
            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'g'){
                return COMMAND.GAMEOVER;
            }
        }
    }

    public void sendInfo(ACTION action, int value) {
        this.info1 = this.info2;
        this.info2 = this.info3;
        this.info3 = String.format(infos.get(action), value);
    }
}
