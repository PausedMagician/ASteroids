package PawzdCO.asteroid;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    @Override
    public void process(World world, GameData gameData) {
        for (Asteroid asteroid : world.getEntities(Asteroid.class)) {
            if (asteroid.getHealth() <= 0) {
                world.removeEntity(asteroid);
                gameData.setScore(gameData.getScore() + 1);
            }
            asteroid.getLocation().add(asteroid.getVelocity());
        }
    }
    
}
