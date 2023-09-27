package MainPackage.gui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import MainPackage.controller.ArenaController;
import java.io.IOException;

public class HowToPlayMenuView extends MenuView{
    @Override
    public void draw(ArenaController arenaController, int option) throws IOException {
        drawGui(arenaController, option);
        arenaController.getScreen().refresh();
    }

    @Override
    public void drawGui(ArenaController arena, int option) {
        TextGraphics graphics = arena.getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(arena.getArenaModel().getWidth(), arena.getArenaModel().getHeight()), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(32, 4), "MOVEMENT");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(16, 6), "Use the keyboard");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(33, 6), "ARROWS");
        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(40, 6), "to move your hero!");

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(32, 8), "ATTACK");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(13, 10), "When your hero is next to the enemies, press");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(58, 10), "A");
        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(60, 10), "to attack them!");

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(32, 12), "PAUSE");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(16, 14), "Press");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(22, 14), "ESC");
        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(26, 14), "to pause your journey.");

        for (int i = 2; i <= 78; i++) {
            graphics.putString(new TerminalPosition(i, 16), "*");
            graphics.putString(new TerminalPosition(i, 2), "*");
        }
        for (int i = 2; i <= 16; i++) {
            graphics.putString(new TerminalPosition(2, i), "*");
            graphics.putString(new TerminalPosition(78, i), "*");
        }

        if(option == 1){
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.putString(new TerminalPosition(26, 20), "Press ENTER to go back");
        }

    }

    @Override
    public ArenaView.COMMAND getCommand(ArenaController arena) throws IOException {
        while(true){
            KeyStroke key = arena.getScreen().readInput();
            if(key.getKeyType() == KeyType.Enter){
                return ArenaView.COMMAND.ENTER;
            }
        }
    }
}
