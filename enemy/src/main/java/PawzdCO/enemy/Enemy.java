package PawzdCO.enemy;

import PawzdCO.common.data.GameData;
import PawzdCO.commonShip.CommonShip;

public class Enemy extends CommonShip {
    
    public Enemy() {
        super();
        this.getPolygon().setFill(javafx.scene.paint.Color.RED);
        this.maxSpeed = 2;
    }

    @Override
    public void kill(GameData gameData) {
        super.kill(gameData);
        // 5 points for killing an enemy
        gameData.addScore(5);
    }

}