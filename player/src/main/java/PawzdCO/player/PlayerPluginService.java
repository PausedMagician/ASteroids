package PawzdCO.player;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IGamePlugin;

public class PlayerPluginService implements IGamePlugin {
    @Override
    public void start(GameData gd, World w) {
        Player player = new Player();
        player.setHealth(5);
        player.setLocation(gd.width / 2, gd.height / 2);

        w.addEntity(player);
    }

    @Override
    public void stop(GameData gd, World w) {
        for (Player player : w.getEntities(Player.class)) {
            w.removeEntity(player);
        }
    }
}
