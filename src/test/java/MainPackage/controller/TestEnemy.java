package MainPackage.controller;

import com.googlecode.lanterna.screen.Screen;
import MainPackage.data.ArenaModel;
import MainPackage.data.Enemy;
import MainPackage.data.Hero;
import MainPackage.data.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class TestEnemy {

    @Test
    public void testEnemySpawn(){
        ArenaModel arenaModel = new ArenaModel(80,24);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arenaModel, screenMock);
        Position position = new Position(5,6);
        Enemy enemy = new Enemy(5,6, 6);

        arenaController.getArenaModel().getEnemies().add(enemy);
        assertEquals(enemy.getPosition(), position);

        assertEquals(enemy.getHP(), 6);

    }

    @Test
    public void testEnemyIsAdj(){
        ArenaModel arenaModel = new ArenaModel(80,24);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arenaModel,screenMock);
        Hero hero = arenaController.getArenaModel().getHero();
        Enemy enemy = arenaController.getArenaModel().getEnemies().get(0);

        arenaController.moveHero(new Position(enemy.getPosition().getX()-1, enemy.getPosition().getY()));

        assertTrue(enemy.isAdjacent(hero.getPosition()));
    }

    @Test
    public void testEnemyTakeDmg(){
        ArenaModel arenaModel = new ArenaModel(80,24);
        Screen screenMock = Mockito.mock(Screen.class);
        ArenaController arenaController = new ArenaController(arenaModel, screenMock);
        Hero hero = arenaController.getArenaModel().getHero();
        Enemy enemy = arenaController.getArenaModel().getEnemies().get(0);

        Position enemyPos = enemy.getPosition();
        arenaController.moveHero(new Position(enemyPos.getX()-1, enemyPos.getY()));


        if (arenaController.canHeroAttack() != -1)
            enemy.takeDmg(hero.getAtk());

        assertNotEquals(3, enemy.getHP());

    }

}
