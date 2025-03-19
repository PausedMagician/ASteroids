package PawzdCO.asteroid;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    private Long lastSpawn;

    @Override
    public void process(World world, GameData gameData) {
        CopyOnWriteArrayList<Asteroid> asteroids = world.getEntities(Asteroid.class);
        Random r = new Random();
        if ((asteroids.isEmpty() || asteroids.size() < Math.min((3 * (gameData.getScore() / 5)), (((gameData.height+gameData.width)/2)/25))) && (lastSpawn == null || System.currentTimeMillis() - lastSpawn > 5000)) {
            Asteroid a = new Asteroid();
            a.setHealth(3);
            a.setMass(a.getRadius());
            if (r.nextBoolean()) {
                // Spawn on left or right side
                // Ouside the screen with 1.5 * radius
                if (r.nextBoolean()) {
                    a.setLocation(r.nextBoolean() ? 0 - a.getRadius() * 1.5 : gameData.width + a.getRadius() * 1.5, r.nextInt(gameData.height));
                } else {
                    a.setLocation(r.nextInt(gameData.width), r.nextBoolean() ? 0 - a.getRadius() * 1.5 : gameData.height + a.getRadius() * 1.5);
                }
            } else {
                // Spawn on top or bottom side
                // Ouside the screen with 1.5 * radius
                if (r.nextBoolean()) {
                    a.setLocation(r.nextInt(gameData.width), r.nextBoolean() ? 0 - a.getRadius() * 1.5 : gameData.height + a.getRadius() * 1.5);
                } else {
                    a.setLocation(r.nextBoolean() ? 0 - a.getRadius() * 1.5 : gameData.width + a.getRadius() * 1.5, r.nextInt(gameData.height));
                }
            }
            a.setPrefferedLocation(a.getLocation());
            a.setVelocity(r.nextDouble(-1, 1), r.nextDouble(-1, 1));
            
            world.addEntity(a);
            
            lastSpawn = System.currentTimeMillis();
        }
        for (Asteroid asteroid : asteroids) {
            if (asteroid.getHealth() <= 0) {
                gameData.setScore(gameData.getScore() + 1);
                asteroid.setAlive(false);
                continue;
            }
            asteroid.getVelocity().max(5);
            asteroid.getPrefferedLocation().add(asteroid.getVelocity());

            
            int neg = (int) (0 - asteroid.getRadius() * 1.5);
            int width = (int) (gameData.width + asteroid.getRadius() * 1.5);
            int height = (int) (gameData.height + asteroid.getRadius() * 1.5);

            if (asteroid.getLocation().getX() < neg) {
                asteroid.getLocation().setX(width);
            } else if (asteroid.getLocation().getX() > width) {
                asteroid.getLocation().setX(neg);
            }
            if (asteroid.getLocation().getY() < neg) {
                asteroid.getLocation().setY(height);
            } else if (asteroid.getLocation().getY() > height) {
                asteroid.getLocation().setY(neg);
            }

        }
    }
    
}
