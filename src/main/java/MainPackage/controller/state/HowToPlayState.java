package MainPackage.controller.state;

import MainPackage.controller.ArenaController;
import MainPackage.gui.ArenaView;
import MainPackage.gui.HowToPlayMenuView;
import MainPackage.gui.MenuView;
import java.io.IOException;

public class HowToPlayState extends ArenaControllerState {
    MenuView gui = new HowToPlayMenuView();
    int option;

    public HowToPlayState(ArenaController arena){
        super(arena);
        option = 1;
    }

    @Override
    protected void draw() throws IOException {
        this.gui.draw(arena, option);
    }

    @Override
    protected void processKey() throws IOException {
        while(true){
            this.draw();
            ArenaView.COMMAND cmd = gui.getCommand(arena);
            if(cmd == ArenaView.COMMAND.ENTER && option == 1){
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
        return true;
    }
}
