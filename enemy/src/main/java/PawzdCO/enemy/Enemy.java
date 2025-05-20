package PawzdCO.enemy;

import PawzdCO.commonShip.CommonShip;

public class Enemy extends CommonShip {
    
    public Enemy() {
        super();
        this.getPolygon().setFill(javafx.scene.paint.Color.RED);
        this.maxSpeed = 2;
    }

}