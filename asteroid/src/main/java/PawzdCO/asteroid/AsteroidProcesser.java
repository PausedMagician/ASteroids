package PawzdCO.asteroid;

import java.util.List;
import java.util.Random;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityProcessingService;

public class AsteroidProcesser implements IEntityProcessingService {

    @Override
    public void process(World world, GameData gameData) {
        List<Asteroid> asteroids = world.getEntities(Asteroid.class);
        
        if (asteroids.stream().filter(e -> e.getRadius() > 10).count() < 5) {
            spawnAsteroid(world, gameData);
        }

        for (Asteroid asteroid : asteroids) {
            if (asteroid.isAlive()) {
                asteroid.getLocation().add(asteroid.getVelocity());
                // Random based on id
                Random random = new Random(asteroid.getId().hashCode());
                float angularVelocity = random.nextInt(-1, 2);
                // the bigger the asteroid, the slower it rotates
                angularVelocity = (float) (angularVelocity * Math.pow(asteroid.getRadius(), -0.5));
                // The faster the asteroid, the faster it rotates
                angularVelocity = (float) (angularVelocity * Math.pow(asteroid.getVelocity().length(), 0.5));
                asteroid.setRotation(asteroid.getRotation() + angularVelocity);
            } else {
                System.out.println("Removing asteroid " + asteroid.getId());
                world.removeEntity(asteroid);
            }
            if (asteroid.getLocation().getX() < 0 || asteroid.getLocation().getX() > gameData.width
                    || asteroid.getLocation().getY() < 0 || asteroid.getLocation().getY() > gameData.height) {
                asteroid.setAlive(false);
            }
        }
    }

    private void spawnAsteroid(World world, GameData gameData) {
        Asteroid asteroid = new Asteroid();
        
        asteroid.setRadius((int) (Math.random() * 20 + 10));

        Vector2 location = new Vector2(1, 1);

        if (Math.random() < 0.5) {
            location.setX(Math.random() * gameData.width);
            if (Math.random() < 0.5) {
                location.setY(0);
            } else {
                location.setY(gameData.height);
            }
        } else {
            location.setY(Math.random() * gameData.height);
            if (Math.random() < 0.5) {
                location.setX(0);
            } else {
                location.setX(gameData.width);
            }
        }

        // Point towards middle of the screen with variation
        Vector2 direction = new Vector2(gameData.width / 2, gameData.height / 2).subtract(location);
        direction = direction.normalize();
        direction.rotate(Math.random() * 20 - 10); // Random rotation between -10 and 10 degrees
        asteroid.setVelocity(direction.multiply(Math.random() + 1)); // Random speed between 1 and 2

        asteroid.setLocation(location);
        asteroid.setRotation(Math.random() * 360);
        asteroid.setHealth(asteroid.getRadius() / 2);
        asteroid.setAlive(true);

        world.addEntity(asteroid);
    }
    
}
