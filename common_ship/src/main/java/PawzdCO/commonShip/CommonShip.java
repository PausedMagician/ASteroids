package PawzdCO.commonShip;

import java.util.ServiceLoader;

import PawzdCO.common.data.Config;
import PawzdCO.common.data.Entity;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.services.IBulletSPI;

public class CommonShip extends Entity {
    public static final double SHIP_SIZING = 5;
    protected double maxSpeed = 5;

    public CommonShip() {
        super();
        double size = SHIP_SIZING * Config.SIZING;
        this.setPolygon(-size, size, 0, -size*1.5f, size, size, 0, size/2);
        this.setRadius((int) size);
    }


    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.getRotation() -90));
        double changeY = Math.sin(Math.toRadians(this.getRotation() -90));
        this.getVelocity().add(new Vector2(changeX, changeY)).max(maxSpeed);
    }

    public void rotateLeft() {
        this.setRotation(this.getRotation() - 3);
    }
    public void rotateRight() {
        this.setRotation(this.getRotation() + 3);
    }

    public void shoot() {
        ServiceLoader.load(IBulletSPI.class).findFirst().ifPresent(
            bulletSPI -> {
                bulletSPI.spawnEntity(this);
            }
        );
    }
}