package PawzdCO.asteroid;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IGamePlugin;

public class AsteroidPlugin implements IGamePlugin {

    @Override
    public void start(GameData gd, World w) {
        System.out.println("AST 2");
    }

    @Override
    public void stop(GameData gd, World w) {
        System.out.println("AST 2 STOP");
    }
}