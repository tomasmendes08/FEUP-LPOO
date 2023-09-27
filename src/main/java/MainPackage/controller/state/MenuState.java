package MainPackage.controller.state;

import MainPackage.controller.ArenaController;
import MainPackage.gui.ArenaView;
import MainPackage.gui.MainMenuView;

import java.io.IOException;

public class MenuState extends ArenaControllerState{
    int option;
    boolean quit;
    MainMenuView gui = new MainMenuView();

    public MenuState(ArenaController arena){
        super(arena);
        option = 1;
        quit = true;
    }

    @Override
    protected void draw() throws IOException {
        this.gui.draw(arena, option);
    }

    @Override
    protected void processKey() throws IOException {
        while (true) {
            this.draw();
            ArenaView.COMMAND cmd = gui.getCommand(arena);
            if (( cmd == ArenaView.COMMAND.DOWN && option == 1) || (cmd == ArenaView.COMMAND.UP && option == 3)) {
                option = 2;
                return;
            }

            if ((cmd == ArenaView.COMMAND.DOWN && option == 2) || (cmd == ArenaView.COMMAND.UP && option == 1)) {
                option = 3;
                return;
            }

            if ((cmd == ArenaView.COMMAND.UP && option == 2) || (cmd == ArenaView.COMMAND.DOWN && option == 3)) {
                option = 1;
                return;
            }

            if (cmd == ArenaView.COMMAND.ENTER && option == 1) {
                arena.setState(new BeginJourneyState(arena));
                return;
            }

            if (cmd == ArenaView.COMMAND.ENTER && option == 2) {
                arena.setState(new HowToPlayState(arena));
                return;
            }

            if (cmd == ArenaView.COMMAND.ENTER && option == 3) {
                arena.getScreen().close();
                quit = false;
                return;
            }
        }
    }

    @Override
    public boolean run() {
        try {
            this.processKey();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return quit;
    }
}
