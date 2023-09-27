package MainPackage.controller.state;

import MainPackage.controller.ArenaController;
import MainPackage.gui.ArenaView;
import MainPackage.gui.GameOverView;

import java.io.IOException;

public class GameOverState extends ArenaControllerState {
    boolean quit;
    GameOverView gui = new GameOverView();

    public GameOverState(ArenaController arena){
        super(arena);
        quit = true;
    }

    @Override
    protected void draw() throws IOException {
        this.gui.draw(arena);
    }

    @Override
    protected void processKey() throws IOException {
        while(true){
            this.draw();
            ArenaView.COMMAND cmd = gui.getCommand(arena);
            if(cmd == ArenaView.COMMAND.GAMEOVER){
                quit = false;
                arena.setState(new MenuState(arena));
                return;
            }
        }
    }

    @Override
    public boolean run() {
        try {
            this.processKey();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return !quit;
    }
}
