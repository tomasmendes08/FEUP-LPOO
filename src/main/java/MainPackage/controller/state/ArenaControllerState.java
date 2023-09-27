package MainPackage.controller.state;

import MainPackage.controller.ArenaController;
import java.io.IOException;

public abstract class ArenaControllerState {
    protected ArenaController arena;

    public ArenaControllerState(ArenaController arena){
        this.arena = arena;
    }

    protected abstract void draw() throws IOException;
    protected abstract void processKey() throws IOException;
    public abstract boolean run();
}
