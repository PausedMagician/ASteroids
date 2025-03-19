package PawzdCO.asteroid;

import java.util.Random;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IGamePlugin;

public class AsteroidPlugin implements IGamePlugin {

    @Override
    public void start(GameData gd, World w) {
        Random r = new Random();
        w.addEntity(new Asteroid() {
            {
                setHealth(1);
                setLocation(gd.width/4, gd.height/4);
                setPrefferedLocation(getLocation());
                setVelocity(new Vector2(r.nextDouble(-1, 1), r.nextDouble(-1, 1)));
            }
        });
    }

    @Override
    public void stop(GameData gd, World w) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }
    
}
