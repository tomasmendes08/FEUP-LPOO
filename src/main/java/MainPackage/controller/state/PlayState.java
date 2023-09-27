package MainPackage.controller.state;

import MainPackage.controller.ArenaController;
import MainPackage.gui.ArenaView;
import java.io.IOException;

public class PlayState extends ArenaControllerState{
    public PlayState(ArenaController arenaController){
        super(arenaController);
    }

    @Override
    protected void draw() throws IOException {
        arena.getScreen().clear();
        arena.getArenaView().drawArena(arena.getArenaModel());
        arena.getScreen().refresh();
    }

    @Override
    protected void processKey() throws IOException {
        boolean quit = false;
        ArenaView.COMMAND cmd = null;

        while(!quit) {
            this.draw();
            cmd = arena.getArenaView().getCommand(arena);
            int gold;

            switch (cmd) {
                case UP:
                    arena.moveHero(arena.getArenaModel().getHero().getPosition().moveUp());
                    break;
                case DOWN:
                    arena.moveHero(arena.getArenaModel().getHero().getPosition().moveDown());
                    break;
                case RIGHT:
                    arena.moveHero(arena.getArenaModel().getHero().getPosition().moveRight());
                    break;
                case LEFT:
                    arena.moveHero(arena.getArenaModel().getHero().getPosition().moveLeft());
                    break;
                case STRIKE:
                    int index = arena.canHeroAttack();
                    if (index > -1) {
                        int damage = 1 +arena.getArenaModel().getHero().getAtk() - arena.getArenaModel().getLevel() ;
                        arena.getArenaView().sendInfo(ArenaView.ACTION.ATTACK, damage);
                        if(arena.getArenaModel().getEnemies().get(index).takeDmg(damage)){
                            arena.getArenaModel().getEnemies().remove(index);
                            int exp = (int) (3+ 0.5*Math.exp(0.3 *arena.getArenaModel().getLevel()));
                            arena.getArenaView().sendInfo(ArenaView.ACTION.GAIN, exp);
                            if (arena.getArenaModel().getHero().gainExp(exp)) arena.getArenaView().sendInfo(ArenaView.ACTION.LEVELUP, arena.getArenaModel().getHero().getLevel());
                        } else {
                            damage = 1 +arena.getArenaModel().getLevel() - (arena.getArenaModel().getHero().getDef() /3);
                            arena.getArenaView().sendInfo(ArenaView.ACTION.DEFEND, damage);
                            if (arena.getArenaModel().getHero().takeDmg(damage)) {
                                quit = true;
                                arena.setState(new GameOverState(arena));
                            }
                        }
                    }
                    break;
                case PAUSE:
                    quit = true;
                    arena.setState(new PauseState(arena));
                    break;
            }
            gold = arena.getArenaModel().pickGold();
            if (gold > 0) {
                arena.getArenaView().sendInfo(ArenaView.ACTION.PICKUP, gold);
            } else if (gold < 0) {
                arena.getArenaView().sendInfo(ArenaView.ACTION.PICKUP, -gold);
                arena.getArenaView().sendInfo(ArenaView.ACTION.HEALTH, 450);
            }
        }
    }

    @Override
    public boolean run() {
        try{
            this.processKey();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
