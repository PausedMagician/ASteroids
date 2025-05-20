package PawzdCO.asteroid_splitter;

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
        int numChildren = (int) (Math.random() * 3) + 2; // Random number between 2 and 4
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
                newAsteroid.setHealth(Math.max(1, childRadius/2));
                
                Vector2 newLocation = new Vector2(location).add(new Vector2(direction).multiply(parentRadius));
                
                newAsteroid.setLocation(newLocation);
                // System.out.println(newAsteroid.getId() + " at " + newAsteroid.getLocation());

                newAsteroid.setVelocity(new Vector2(direction).multiply(entity.getVelocity().length() / 2).add(new Vector2(entity.getVelocity()).divide(numChildren)));
                newAsteroid.setRotation(Math.random() * 360);
                
                direction.rotate(360.0 / numChildren);
                AsteroidSplitter.world.addEntity(newAsteroid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}