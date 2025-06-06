package PawzdCO.main;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.World;

public class EntityTest {

    static World world;
    static Entity testEntity;

    @BeforeEach
    void init() {
        world = new World();
        testEntity = new Entity();
        world.addEntity(testEntity);
    }

    @Test
    void testInsertion() {
        int initialSize = world.getEntities().size();
        world.addEntity(new Entity());
        assertTrue(world.getEntities().size() > initialSize);
    }

    @Test
    void testRemoval() {
        int initialSize = world.getEntities().size();
        world.removeEntity(testEntity);
        assertTrue(world.getEntities().size() < initialSize);
    }

    @Test
    void test200() {
        int initialSize = world.getEntities().size();
        for (int i = 0; i < 200; i++) {
            world.addEntity(new Entity());
        }
        assertTrue(world.getEntities().size() == initialSize + 200);
    }

    @Test
    void process200() {
        int initialSize = world.getEntities().size();
        for (int i = 0; i < 200; i++) {
            world.addEntity(new Entity());
        }

        Main main = spy(new Main());
        main.gameData = spy(main.gameData);
        main.world = spy(main.world);

        // run 1 tick
        main.tick();
        // Check if update() and render() were called
        verify(main, times(1)).update();
        verify(main, times(1)).render();
    }
}
