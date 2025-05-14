package PawzdCO.bullet;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IEntityProcessingService;

public class BulletProcessingService implements IEntityProcessingService {

    @Override
    public void process(World world, GameData gameData) {
        for (Bullet bullet : world.getEntities(Bullet.class)) {
            if (bullet.isAlive()) {
                bullet.getLocation().add(bullet.getVelocity());
            } else {
                world.removeEntity(bullet);
            }
            if (bullet.getLocation().getX() < 0 || bullet.getLocation().getX() > gameData.width
                    || bullet.getLocation().getY() < 0 || bullet.getLocation().getY() > gameData.height) {
                bullet.kill();
            }

            bullet.getLocation().add(bullet.getVelocity());
        }
    }
    
}