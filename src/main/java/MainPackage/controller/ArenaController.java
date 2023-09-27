package MainPackage.controller;

import com.googlecode.lanterna.screen.Screen;
import MainPackage.controller.state.ArenaControllerState;
import MainPackage.data.*;
import MainPackage.gui.ArenaView;

public class ArenaController {
    private ArenaView arenaView;
    private ArenaModel arenaModel;
    private Screen screen;
    private ArenaControllerState state;

    public ArenaController(ArenaModel arenaModel, Screen screen){
        this.arenaModel = arenaModel;
        this.screen = screen;

        this.arenaView = new ArenaView(arenaModel.getHeight(), this.screen);

    }

    public void run() {
        while(state.run());
    }

    public void moveHero(Position position){
        if(canHeroMove(position)) {
            arenaModel.getHero().setPosition(position);
            if (position.equals(arenaModel.getLevelUP().getPosition())) {
                arenaModel.newLevel();
                arenaView.sendInfo(ArenaView.ACTION.PROGRESS, arenaModel.getLevel());
            }
        }
    }

    public boolean canHeroMove(Position position){
        for (Walls w : arenaModel.getLayout().getWalls()) {
            if (w.getPosition().equals(position)) return false;
        }
        for(Enemy e : arenaModel.getEnemies()){
            if(e.getPosition().equals(position)) return false;
        }
        return true;
    }

    public int canHeroAttack() {
        int i = -1;
        for (Enemy e : arenaModel.getEnemies()) {
            i++;
            if (e.isAdjacent(arenaModel.getHero().getPosition())) return i;
        }
        return -1;
    }

    public Screen getScreen(){
        return screen;
    }

    public ArenaModel getArenaModel() {
        return arenaModel;
    }

    public ArenaView getArenaView() {
        return arenaView;
    }

    public void setArenaModel(ArenaModel arenaModel) {
        this.arenaModel = arenaModel;
    }

    public void setState(ArenaControllerState state) {
        this.state = state;
    }

    public ArenaControllerState getState() {
        return state;
    }

    public void setArenaView(ArenaView arenaView) {
      this.arenaView = arenaView;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}

