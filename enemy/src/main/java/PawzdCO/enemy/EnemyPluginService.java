package PawzdCO.enemy;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IGamePlugin;

public class EnemyPluginService implements IGamePlugin {

    @Override
    public void start(GameData gd, World w) {
        
    }

    @Override
    public void stop(GameData gd, World w) {
        for (Enemy enemy : w.getEntities(Enemy.class)) {
            w.removeEntity(enemy);
        }
    }
    
}
