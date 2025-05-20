package PawzdCO.collision;

import java.util.List;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityPostProcessingService;

public class Collision implements IEntityPostProcessingService {

    @Override
    public void process(World world, GameData gameData) {
        List<Entity> entities = world.getEntities();

        for (int i = 0; i < entities.size(); i++) {
            Entity entityA = entities.get(i);
            if (!entityA.isAlive()) continue;
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entityB = entities.get(j);
                if (!entityB.isAlive()) continue;
                if (!entityA.isAlive()) continue;
                if (entityA.equals(entityB)) continue;
                // Check if locations and radius collide
                if (entityA.getLocation().distance(entityB.getLocation()) < entityA.getRadius() + entityB.getRadius()) {
                    entityA.setHealth(entityA.getHealth() - 1);
                    entityB.setHealth(entityB.getHealth() - 1);

                    if (entityA.getHealth() <= 0) {
                        entityA.kill();
                    }
                    if (entityB.getHealth() <= 0) {
                        entityB.kill();
                    }
                }

            }
        }
    }
    
}