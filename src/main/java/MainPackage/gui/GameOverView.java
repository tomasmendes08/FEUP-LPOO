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

public class GameOverView {

    public void draw(ArenaController arenaController) throws IOException {
        drawGameOver(arenaController);
        arenaController.getScreen().refresh();
    }

    public void drawGameOver(ArenaController arena) {
        TextGraphics graphics = arena.getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(arena.getArenaModel().getWidth(), arena.getArenaModel().getHeight()), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(35, 10), "GAME OVER");
        graphics.enableModifiers(SGR.BLINK);
        graphics.putString(new TerminalPosition(30, 14), "Press G to continue");
        graphics.disableModifiers(SGR.BOLD);
        graphics.disableModifiers(SGR.BLINK);
    }

    public ArenaView.COMMAND getCommand(ArenaController arena) throws IOException {
        while(true){
            KeyStroke key = arena.getScreen().readInput();

            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'g'){
                return ArenaView.COMMAND.GAMEOVER;
            }
        }
    }
}
