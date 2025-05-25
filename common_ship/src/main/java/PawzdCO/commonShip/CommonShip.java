package PawzdCO.commonShip;

import java.util.ServiceLoader;

import PawzdCO.common.data.Config;
import PawzdCO.common.data.Entity;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.services.IBulletSPI;

public class CommonShip extends Entity {
    public static final double SHIP_SIZING = 5;
    /**
     * Max speed of the ship. When it hits this it gets hard limited.
     */
    protected double maxSpeed = 5;
    /**
     * Value for when the last time it shot in milliseconds
     */
    protected long lastShot = 0;
    /**
     * Value for how much time must pass between shots
     */
    protected long shootingDelay = 500;

    public CommonShip() {
        super();
        double size = SHIP_SIZING * Config.SIZING;
        this.setPolygon(-size, size, 0, -size*1.5f, size, size, 0, size/2);
        this.setRadius((int) size);
    }

    /**
     * Makes the ship accelerate towards wherever it's facing.
     */
    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.getRotation() -90));
        double changeY = Math.sin(Math.toRadians(this.getRotation() -90));
        this.getVelocity().add(new Vector2(changeX, changeY)).max(maxSpeed);
    }

    /**
     * Makes the ship rotate left...
     */
    public void rotateLeft() {
        this.setRotation(this.getRotation() - 3);
    }
    /**
     * Makes the ship rotate right...
     */
    public void rotateRight() {
        this.setRotation(this.getRotation() + 3);
    }

    /**
     * For caching purposes
     */
    protected static IBulletSPI bulletSpi = null;

    /**
     * <p>Uses a IBulletSPI to spawn bullets.</p>
     * Limited firerate builtin using shootingDelay (<strong>long</strong>)
     */
    public void shoot() {
        if (lastShot + shootingDelay < System.currentTimeMillis()) {
            if (CommonShip.bulletSpi != null) {
                bulletSpi.spawnEntity(this);
            } else {
                ServiceLoader.load(IBulletSPI.class).findFirst().ifPresent(
                    bulletSPI -> {
                        bulletSPI.spawnEntity(this);
                        CommonShip.bulletSpi = bulletSPI;
                    }
                );
            }
            lastShot = System.currentTimeMillis();
        }
    }
}