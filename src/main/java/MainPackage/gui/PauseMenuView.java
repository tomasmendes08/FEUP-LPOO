package MainPackage.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import MainPackage.controller.ArenaController;

import java.io.IOException;

public class PauseMenuView extends MenuView {

    @Override
    public void draw(ArenaController arenaController, int option) throws IOException {
        arenaController.getScreen().clear();
        drawGui(arenaController, option);
        arenaController.getScreen().refresh();
    }

    @Override
    public void drawGui(ArenaController arena, int option) {
        TextGraphics graphics = arena.getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(arena.getArenaModel().getWidth(), arena.getArenaModel().getHeight()), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(36, 8), "PAUSE");
        graphics.disableModifiers(SGR.BOLD);

        for (int i = 30; i <= 48; i++) {
            graphics.putString(new TerminalPosition(i, 19), "*");
            graphics.putString(new TerminalPosition(i, 12), "*");
        }
        for (int i = 12; i <= 19; i++) {
            graphics.putString(new TerminalPosition(30, i), "*");
            graphics.putString(new TerminalPosition(48, i), "*");
        }

        if(option == 1){
            graphics.putString(new TerminalPosition(33, 15), "MAIN MENU");
            graphics.putString(new TerminalPosition(33, 17), "EXIT GAME");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(33, 13), "RESUME");
        }
        if(option == 2){
            graphics.putString(new TerminalPosition(33, 13), "RESUME");
            graphics.putString(new TerminalPosition(33, 17), "EXIT GAME");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(33, 15), "MAIN MENU");
        }
        if(option == 3){
            graphics.putString(new TerminalPosition(33, 13), "RESUME");
            graphics.putString(new TerminalPosition(33, 15), "MAIN MENU");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(33, 17), "EXIT GAME");
        }

    }
}
