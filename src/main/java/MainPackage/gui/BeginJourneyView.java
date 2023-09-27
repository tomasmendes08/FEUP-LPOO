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

public class BeginJourneyView extends MenuView{

    @Override
    public void draw(ArenaController arenaController, int option) throws IOException {
        drawGui(arenaController, option);
        arenaController.getScreen().refresh();
    }

    @Override
    public void drawGui(ArenaController arena, int option) {
        TextGraphics graphics = arena.getScreen().newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(arena.getArenaModel().getWidth(), arena.getArenaModel().getHeight()), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.putString(new TerminalPosition(21, 7), "Your");
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(26, 7), "hero");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(31, 7), "is about to begin his adventure!");

        graphics.putString(new TerminalPosition(10, 12), "Kill all");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(19,12), "enemies");
        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(27,12),"in the room to get EXP");
        graphics.putString(new TerminalPosition(49,12),", your");
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(56,12),"hero");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(61,12), "LVL UP!");

        graphics.putString(new TerminalPosition(10, 14), "Try to catch all the");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(31,14), "gold");
        graphics.disableModifiers(SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(36,14), "in the room, your");
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(54,14), "hero");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(59,14), "will get full HP!");

        graphics.putString(new TerminalPosition(10, 16), "Progress through the dungeon by going down the");
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(57,16),"stairs");
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(63,16),"!");

        if(option == 1){
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.enableModifiers(SGR.BLINK);
            graphics.putString(new TerminalPosition(26, 22), "Press ENTER to start journey");
            graphics.disableModifiers(SGR.BLINK);
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
