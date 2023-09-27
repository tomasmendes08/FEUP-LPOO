package MainPackage.controller;

import com.googlecode.lanterna.screen.Screen;
import MainPackage.data.ArenaModel;
import MainPackage.data.Position;
import MainPackage.gui.ArenaView;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestHero {
    @Test
    public void testArenaCreatingHero() throws IOException {
        ArenaModel arena = new ArenaModel(60, 20);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arena, screenMock);
        ArenaView arenaView = new ArenaView(20, arenaController.getScreen());

        Position position = new Position(0,0);

        arenaController.moveHero(position);
        assertNotEquals(position, arena.getHero().getPosition());

        Position position1 = new Position(4,4);
        arenaController.moveHero(position1);

        assertEquals(position1, arena.getHero().getPosition());

    }

    @Test
    public void testArenaHeroMovingUp() throws IOException{
        ArenaModel arena = new ArenaModel(60, 20);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arena, screenMock);
        ArenaView arenaView = new ArenaView(20, arenaController.getScreen());

        Position position = new Position(4,4);
        arenaController.moveHero(position);

        assertEquals(new Position(4,3), arena.getHero().getPosition().moveUp());
    }

    @Test
    public void testArenaHeroMovingDown() throws IOException{
        ArenaModel arena = new ArenaModel(60, 20);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arena,screenMock);
        ArenaView arenaView = new ArenaView(20, arenaController.getScreen());

        Position position = new Position(4,4);
        arenaController.moveHero(position);

        assertEquals(new Position(4,5), arena.getHero().getPosition().moveDown());
    }

    @Test
    public void testArenaHeroMovingRight() throws IOException{
        ArenaModel arena = new ArenaModel(60, 20);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arena, screenMock);
        ArenaView arenaView = new ArenaView(20, arenaController.getScreen());

        Position position = new Position(4,4);
        arenaController.moveHero(position);

        assertEquals(new Position(5,4), arena.getHero().getPosition().moveRight());
    }

    @Test
    public void testArenaHeroMovingLeft() throws IOException{
        ArenaModel arena = new ArenaModel(60, 20);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arena, screenMock);
        ArenaView arenaView = new ArenaView(20, arenaController.getScreen());

        Position position = new Position(4,4);
        arenaController.moveHero(position);

        assertEquals(new Position(3,4), arena.getHero().getPosition().moveLeft());
    }

    @Test
    public void testArenaMoveHeroIntoCoins() throws IOException{
        ArenaModel arena = new ArenaModel(60, 20);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arena, screenMock);
        ArenaView arenaView = new ArenaView(20, arenaController.getScreen());

        Position position = new Position(4,4);
        arenaController.moveHero(position);

        arena.createGold();

        for(int i = 0; i < arena.getGold().size(); i++){
            Position position_aux = arena.getGold().get(i).getPosition();
            arenaController.moveHero(position_aux);
            assertEquals(position_aux, arena.getHero().getPosition());
        }
    }

}
