package PawzdCO.player;
import PawzdCO.common.data.Entity;
import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.data.GameData.Keys;
import PawzdCO.common.services.IEntityProcessingService;
import PawzdCO.common.services.IGamePlugin;
import javafx.scene.shape.Polygon;

public class Player extends Entity implements IEntityProcessingService, IGamePlugin {

    public Player() {

    }

    @Override
    public void process(World world, GameData gameData) {
        for (Player player : world.getEntities(Player.class)) {
            if (gameData.isPressed(Keys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.getLocation().addX(changeX);
                player.getLocation().addY(changeY);
            }
            if (gameData.isPressed(Keys.RIGHT)) {
                player.setRotation(player.getRotation() + 3);
            }
            if (gameData.isPressed(Keys.LEFT)) {
                player.setRotation(player.getRotation() - 3);
            }
        }
    }

    @Override
    public void start(GameData gd, World w) {
        Player e = new Player();
        e.setHealth(5);
        e.setRadius(5);
        e.setLocation(gd.width/2, gd.height/2);
        e.setPolygon(new Polygon(-5,-5,10,0,-5,5));
        w.addEntity(e);
        Player l = new Player();
        l.setHealth(5);
        l.setRadius(5);
        l.setLocation(gd.width/2, gd.height/3);
        l.setPolygon(new Polygon(-5,-5,10,0,-5,5));
        l.setRotation(180);
        w.addEntity(l);
    }

    @Override
    public void stop(GameData gd, World w) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }
    
}