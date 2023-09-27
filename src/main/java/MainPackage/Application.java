package MainPackage;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import MainPackage.controller.ArenaController;
import MainPackage.controller.state.MenuState;
import MainPackage.data.ArenaModel;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try{
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

            ArenaModel arenaModel = new ArenaModel(80, 24);

            ArenaController arenaController = new ArenaController(arenaModel, screen);
            arenaController.setState(new MenuState(arenaController));
            arenaController.run();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}

