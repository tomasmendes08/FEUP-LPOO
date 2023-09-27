package MainPackage.gui;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import MainPackage.controller.ArenaController;

import java.io.IOException;

public abstract class MenuView {
    public abstract void draw(ArenaController arenaController, int option) throws IOException;
    public abstract void drawGui(ArenaController arena, int option);
    public ArenaView.COMMAND getCommand(ArenaController arena) throws IOException {
        while(true){
            KeyStroke key = arena.getScreen().readInput();
            if(key.getKeyType() == KeyType.ArrowUp) {
                return ArenaView.COMMAND.UP;
            }
            if(key.getKeyType() == KeyType.ArrowDown){
                return ArenaView.COMMAND.DOWN;
            }
            if(key.getKeyType() == KeyType.Enter){
                return ArenaView.COMMAND.ENTER;
            }
        }
    }
}
