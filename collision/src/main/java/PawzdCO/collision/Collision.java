package PawzdCO.collision;

import PawzdCO.common.data.Entity;
import PawzdCO.common.data.GameData;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityPostProcessingService;

public class Collision implements IEntityPostProcessingService {

    @Override
    public void process(World world, GameData gameData) {
        for (Entity e : world.getEntities()) {
            for (Entity n : world.getEntities()) {
                if (e.getId().equals(n.getId())) {
                    continue;
                }
                if (e.getHealth() <= 0 && n.getHealth() <= 0)
                    continue;
                if (collides(e, n)) {
                    System.out.println("Collision!");
                    e.setHealth(e.getHealth() - 1);
                    n.setHealth(n.getHealth() - 1);
                    if (e.getHealth() > 0 && n.getHealth() > 0) {
                        // Push entities away
                        Vector2 delta = e.getLocation().difference(n.getLocation());
                        delta.normalize();
                        delta.multiplySelf(5);
                        e.getLocation().add(delta);
                        n.getLocation().add(delta.multiply(-1));
                    }
                }
            }
        }
    }

    boolean collides(Entity e, Entity n) {
        double distance = e.getLocation().difference(n.getLocation()).length();
        return distance < e.getRadius() + n.getRadius();
    }
    
}