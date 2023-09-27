package MainPackage.controller;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import MainPackage.controller.state.*;
import MainPackage.data.ArenaModel;
import MainPackage.data.Hero;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestState {

    @Test
    public void testMenuState() throws IOException {
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaModel arena = new ArenaModel(80,24);
        //ArenaView view = new ArenaView(arena.getWidth(), arena.getHeight(), screenMock);
        ArenaController arenaController = new ArenaController(arena, screenMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        //Enter Begin Journey
        ArenaControllerState arenaControllerState = new MenuState(arenaController);
        arenaController.setState(arenaControllerState);

        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        arenaControllerState.run();
        assertEquals(BeginJourneyState.class, arenaController.getState().getClass());

        //Enter How To Play
        arenaController = new ArenaController(arena, screenMock);
        arenaControllerState = new MenuState(arenaController);
        arenaController.setState(arenaControllerState);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        arenaControllerState.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        arenaControllerState.run();
        assertEquals(HowToPlayState.class, arenaController.getState().getClass());

        //Enter Exit Game
        arenaController = new ArenaController(arena, screenMock);
        arenaControllerState = new MenuState(arenaController);
        arenaController.setState(arenaControllerState);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        arenaControllerState.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        arenaControllerState.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        assertFalse(arenaControllerState.run());
    }

    @Test
    public void TestPauseState() throws IOException {
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaModel arenaModel = new ArenaModel(80,24);
        ArenaController arenaController = new ArenaController(arenaModel, screenMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        ArenaControllerState pauseState = new PauseState(arenaController);

        arenaController.setState(pauseState);

        //Enter Resume Game
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        pauseState.run();
        doReturn(new KeyStroke(KeyType.ArrowUp, false, false)).when(screenMock).readInput();
        pauseState.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        pauseState.run();
        assertEquals(PlayState.class, arenaController.getState().getClass());

        //Enter Main Menu
        arenaController = new ArenaController(arenaModel, screenMock);
        pauseState = new PauseState(arenaController);
        arenaController.setState(pauseState);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        pauseState.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        pauseState.run();
        assertEquals(MenuState.class, arenaController.getState().getClass());

        //Enter Exit Game
        arenaController = new ArenaController(arenaModel, screenMock);
        pauseState = new PauseState(arenaController);
        arenaController.setState(pauseState);

        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        pauseState.run();
        doReturn(new KeyStroke(KeyType.ArrowDown, false, false)).when(screenMock).readInput();
        pauseState.run();
        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        pauseState.run();
        assertFalse(pauseState.run());
    }

    @Test
    public void testGameOverState() throws IOException {
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaModel arenaModel = new ArenaModel(80,24);
        ArenaController arenaController = new ArenaController(arenaModel, screenMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        ArenaControllerState gameOverState = new GameOverState(arenaController);


        doReturn(new KeyStroke('g', false, false)).when(screenMock).readInput();
        gameOverState.run();
        assertEquals(MenuState.class, arenaController.getState().getClass());

    }

    @Test
    public void testHowToPlayState() throws IOException{
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaModel arenaModel = new ArenaModel(80,24);
        ArenaController arenaController = new ArenaController(arenaModel, screenMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        ArenaControllerState howToPlayState = new HowToPlayState(arenaController);

        arenaController.setState(howToPlayState);

        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        howToPlayState.run();
        assertEquals(MenuState.class, arenaController.getState().getClass());
    }

    @Test
    public void testBeginJourneyState() throws IOException{
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaModel arenaModel = new ArenaModel(80,24);
        ArenaController arenaController = new ArenaController(arenaModel, screenMock);

        TextGraphics graphicsMock = Mockito.mock(TextGraphics.class);
        doReturn(graphicsMock).when(screenMock).newTextGraphics();

        ArenaControllerState beginJourneyState = new BeginJourneyState(arenaController);

        arenaController.setState(beginJourneyState);

        doReturn(new KeyStroke(KeyType.Enter, false, false)).when(screenMock).readInput();
        beginJourneyState.run();
        assertEquals(PlayState.class, arenaController.getState().getClass());
    }

}
