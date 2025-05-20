package PawzdCO.asteroid;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IGamePlugin;

public class AsteroidPlugin implements IGamePlugin {

    @Override
    public void start(GameData gd, World w) {
        System.out.println("AST1");

        Asteroid asteroid = new Asteroid();

        asteroid.getLocation().addX(200);
        asteroid.getLocation().addY(200);

        w.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gd, World w) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }
    
}
