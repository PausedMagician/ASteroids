package PawzdCO.asteroid;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    @Override
    public void process(World world, GameData gameData) {
        CopyOnWriteArrayList<Asteroid> asteroids = world.getEntities(Asteroid.class);
        if (asteroids.isEmpty() || asteroids.size() < (10 * (gameData.getScore() % 10))) {
            Asteroid a = new Asteroid();
            a.setHealth(3);
            Random r = new Random();
            if (r.nextBoolean()) {
                a.setLocation(r.nextBoolean() ? 0 : gameData.width, r.nextInt(gameData.height));
            } else {
                a.setLocation(r.nextInt(gameData.width), r.nextBoolean() ? 0 : gameData.height);
            }
            a.setPrefferedLocation(a.getLocation());
            a.setVelocity(r.nextDouble(-1, 1), r.nextDouble(-1, 1));
            
            world.addEntity(a);
        }
        for (Asteroid asteroid : asteroids) {
            if (asteroid.getHealth() <= 0) {
                gameData.setScore(gameData.getScore() + 1);
                asteroid.setAlive(false);
                continue;
            }
            asteroid.getPrefferedLocation().add(asteroid.getVelocity());
        }
    }
    
}
