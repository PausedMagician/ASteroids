package PawzdCO.player;

import PawzdCO.common.data.Config;
import PawzdCO.common.data.Entity;

public class Player extends Entity {
    
    public static final double PLAYER_SIZING = 5;

    public Player() {
        super();
        double size = PLAYER_SIZING * Config.SIZING;
        this.setPolygon(-size, size, 0, -size*1.5f, size, size, 0, size/2);
        this.setRadius((int)size);
    }
}