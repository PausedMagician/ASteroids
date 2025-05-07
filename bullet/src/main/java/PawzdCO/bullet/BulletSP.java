package PawzdCO.bullet;
import PawzdCO.common.data.Entity;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.services.IEntitySpawnInterface;

public class BulletSP implements IEntitySpawnInterface {

    @Override
    public void spawnEntity(Entity parent, Object world) {
        Bullet bullet = new Bullet();


        // Set the bullet's properties based on the parent entity

        double changeX = Math.cos(Math.toRadians(parent.getRotation() -90)) + 1;
        double changeY = Math.sin(Math.toRadians(parent.getRotation() -90)) + 1;
        

        bullet.setLocation(new Vector2(parent.getLocation()).add(new Vector2(changeX, changeY)));

        bullet.setVelocity(new Vector2(changeX, changeY));
        bullet.getVelocity().max(5);
    }
    
}
