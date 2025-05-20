package PawzdCO.asteroid_splitter;

import java.util.Random;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IAsteroidSPI;
import PawzdCO.common.services.IWorldAware;

public class AsteroidSplitter implements IAsteroidSPI, IWorldAware {

    private static World world;

    @Override
    public void provideWorld(World world) {
        AsteroidSplitter.world = world;
    }

    @Override
    public void spawnEntity(Entity entity) {
        int parentRadius = entity.getRadius();

        // Spawn 2-4 smaller asteroids
        // The bigger the asteroid, the more children it spawns
        int numChildren = new Random().nextInt(2, Math.max(4, Math.ceilDiv(parentRadius, 3)));
        // System.out.println("Spawning " + numChildren + " children for asteroid " + entity.getId());

        Vector2 location = new Vector2(entity.getLocation());
        Vector2 direction = new Vector2(0, 1);
        direction.rotate(Math.random() * 360); // random starting direction
        // System.out.println(location);

        for (int i = 0; i < numChildren; i++) {
            // Calculate the radius of the child asteroid
            int childRadius = parentRadius / 2;

            // Create a new entity for the child asteroid
            try {
                Entity newAsteroid = entity.getClass().getConstructor().newInstance();
                newAsteroid.setRadius(childRadius);
                newAsteroid.setHealth(Math.ceilDiv(childRadius, parentRadius));
                
                Vector2 newLocation = new Vector2(location).add(new Vector2(direction).multiply(parentRadius));
                
                newAsteroid.setLocation(newLocation);
                // System.out.println(newAsteroid.getId() + " at " + newAsteroid.getLocation());

                newAsteroid.setVelocity(new Vector2(direction).rotate(Math.random() * 20 - 10).multiply(Math.max(0.2, entity.getVelocity().length() / 2)).add(new Vector2(entity.getVelocity()).divide(numChildren)));
                // newAsteroid.setVelocity(new Vector2(direction));
                newAsteroid.setRotation(Math.random() * 360);
                
                AsteroidSplitter.world.addEntity(newAsteroid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            direction.rotate(360.0 / numChildren);
        }
    }
    
}