package MainPackage.controller.state;

import MainPackage.controller.ArenaController;
import MainPackage.data.ArenaModel;
import MainPackage.gui.ArenaView;
import MainPackage.gui.BeginJourneyView;
import MainPackage.gui.MenuView;
import java.io.IOException;

public class BeginJourneyState extends ArenaControllerState {
    int option;
    MenuView gui = new BeginJourneyView();

    public BeginJourneyState(ArenaController arena){
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
                arena.setState(new PlayState(arena));
                resetGame();
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

    private void resetGame() throws IOException {
        this.arena.setArenaModel(new ArenaModel(80,24));
        this.arena.setArenaView(new ArenaView(24, arena.getScreen()));
    }
}
