package PawzdCO.player;

import PawzdCO.commonShip.CommonShip;

public class Player extends CommonShip {
    
    public Player() {
        super();
        this.getPolygon().setFill(javafx.scene.paint.Color.BLUE);
    }
}