package PawzdCO.player;
import PawzdCO.common.data.Entity;
import PawzdCO.common.data.GameData;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.data.GameData.Keys;
import PawzdCO.common.services.IEntityProcessingService;
import PawzdCO.common.services.IGamePlugin;
import javafx.scene.shape.Polygon;

public class Player extends Entity implements IEntityProcessingService, IGamePlugin {

    private double speed = 5;
    private double acceleration = 0.1;

    public Player() {

    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void process(World world, GameData gameData) {
        for (Player player : world.getEntities(Player.class)) {

            player.getPrefferedLocation().add(player.getVelocity());
            
            if (gameData.isPressed(Keys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation())) * acceleration;
                double changeY = Math.sin(Math.toRadians(player.getRotation())) * acceleration;
                player.getVelocity().add(new Vector2(changeX, changeY)).max(speed);
            } else {
                player.getVelocity().lerp(new Vector2(), 0.02f);
            }
            if (gameData.isPressed(Keys.RIGHT)) {
                player.setRotation(player.getRotation() + 3);
            }
            if (gameData.isPressed(Keys.LEFT)) {
                player.setRotation(player.getRotation() - 3);
            }
            break;
        }
    }

    @Override
    public void start(GameData gd, World w) {
        Player e = new Player();
        e.setHealth(999999);
        e.setRadius(5);
        e.setLocation(gd.width/2, gd.height/2);
        e.setPrefferedLocation(e.getLocation());
        e.setPolygon(new Polygon(-5,-5,10,0,-5,5));
        w.addEntity(e);
        Player l = new Player();
        l.setHealth(5000);
        l.setRadius(5);
        l.setLocation(gd.width/2, gd.height/3);
        l.setPrefferedLocation(l.getLocation());
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