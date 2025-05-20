package PawzdCO.player;

import java.util.ServiceLoader;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.data.GameData.Keys;
import PawzdCO.common.services.IBulletSPI;
import PawzdCO.common.services.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService{

    @Override
    public void process(World world, GameData gameData) {
        
        for (Player player : world.getEntities(Player.class)) {
            if (player.isAlive()) {

                player.getLocation().add(player.getVelocity());

                if (player.getLocation().getX() < 0) {
                    player.getLocation().setX(gameData.width - 1);
                } else if (player.getLocation().getX() > gameData.width) {
                    player.getLocation().setX(1);
                }
                if (player.getLocation().getY() < 0) {
                    player.getLocation().setY(gameData.height - 1);
                } else if (player.getLocation().getY() > gameData.height) {
                    player.getLocation().setY(1);
                }

                double changeX = Math.cos(Math.toRadians(player.getRotation() -90));
                double changeY = Math.sin(Math.toRadians(player.getRotation() -90));
                
                if (gameData.isDown(Keys.UP)) {
                    player.getVelocity().add(new Vector2(changeX, changeY)).max(5);
                }
                if (gameData.isDown(Keys.RIGHT)) {
                    player.setRotation(player.getRotation() + 3);
                }
                if (gameData.isDown(Keys.LEFT)) {
                    player.setRotation(player.getRotation() - 3);
                }
                if (gameData.isPressed(Keys.SPACE)) {
                    ServiceLoader.load(IBulletSPI.class).findFirst().ifPresent(
                        bulletSPI -> {
                            bulletSPI.spawnEntity(player);
                        }
                    );
                }

                player.getVelocity().lerp(new Vector2(0,0), 0.02f);

            }
        } 

    }
    
}
