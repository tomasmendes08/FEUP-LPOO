package MainPackage.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import MainPackage.controller.ArenaController;
import java.io.IOException;

public class MainMenuView extends MenuView{

    @Override
    public void draw(ArenaController arenaController, int option) throws IOException {
        drawGui(arenaController, option);
        arenaController.getScreen().refresh();
    }

    @Override
    public void drawGui(ArenaController arena, int option){
        TextGraphics graphics = arena.getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getArenaModel().getWidth(), arena.getArenaModel().getHeight()), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        drawTitle(graphics);

        for (int i = 30; i <= 48; i++) {
            graphics.putString(new TerminalPosition(i, 19), "*");
            graphics.putString(new TerminalPosition(i, 12), "*");
        }
        for (int i = 12; i <= 19; i++) {
            graphics.putString(new TerminalPosition(30, i), "*");
            graphics.putString(new TerminalPosition(48, i), "*");
        }

        if(option == 1){
            graphics.putString(new TerminalPosition(33, 15), "HOW TO PLAY");
            graphics.putString(new TerminalPosition(33, 17), "EXIT GAME");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(33, 13), "START JOURNEY");
        }
        if(option == 2){
            graphics.putString(new TerminalPosition(33, 13), "START JOURNEY");
            graphics.putString(new TerminalPosition(33, 17), "EXIT GAME");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(33, 15), "HOW TO PLAY");
        }
        if(option == 3){
            graphics.putString(new TerminalPosition(33, 13), "START JOURNEY");
            graphics.putString(new TerminalPosition(33, 15), "HOW TO PLAY");
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.putString(new TerminalPosition(33, 17), "EXIT GAME");
        }
    }

    private void drawTitle(TextGraphics graphics){
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(new TerminalPosition(2,1),"O O O   O O O    O O O    O     O  O O O O     O O O   O      O  O O O O");
        graphics.putString(new TerminalPosition(2,2),"O	  O  O     O  O     O   O     O  O          O     O  O O    O  O");
        graphics.putString(new TerminalPosition(2,3),"O	  O  O     O  O         O     O  O          O     O  O  O   O  O");
        graphics.putString(new TerminalPosition(2,4),"O O O  O     O  O  O O O  O     O  O O O      O     O  O   O  O  O O O");
        graphics.putString(new TerminalPosition(2,5),"O O    O     O  O    O    O     O  O          O     O  O    O O  O");
        graphics.putString(new TerminalPosition(2,6),"O	 O    O O O    O O O     O O O   O O O O     O O O   O      O  O O O O");

        graphics.enableModifiers(SGR.BLINK);
        graphics.putString(new TerminalPosition(32, 9), "A Hero's Tale!");
        graphics.disableModifiers(SGR.BOLD);
        graphics.disableModifiers(SGR.BLINK);
    }

}
