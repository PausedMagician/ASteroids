package PawzdCO.player;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.data.GameData.Keys;
import PawzdCO.common.services.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService{

    @Override
    public void process(World world, GameData gameData) {
        
        for (Player player : world.getEntities(Player.class)) {
            if (player.isAlive()) {

                player.getLocation().add(player.getVelocity());

                double changeX = Math.cos(Math.toRadians(player.getRotation() -90));
                double changeY = Math.sin(Math.toRadians(player.getRotation() -90));
                
                if (gameData.isPressed(Keys.UP)) {
                    player.getVelocity().add(new Vector2(changeX, changeY)).max(5);
                }
                if (gameData.isPressed(Keys.RIGHT)) {
                    player.setRotation(player.getRotation() + 3);
                }
                if (gameData.isPressed(Keys.LEFT)) {
                    player.setRotation(player.getRotation() - 3);
                }

                player.getVelocity().lerp(new Vector2(0,0), 0.02f);

            }
        } 

    }
    
}
