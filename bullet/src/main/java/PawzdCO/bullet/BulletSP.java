package PawzdCO.bullet;
import PawzdCO.common.data.Entity;
import PawzdCO.common.data.Vector2;
import PawzdCO.common.data.World;
import PawzdCO.common.services.IBulletSPI;
import PawzdCO.common.services.IWorldAware;

public class BulletSP implements IBulletSPI, IWorldAware {

    private static World world;

    @Override
    public void provideWorld(World world) {
        BulletSP.world = world;
    }

    @Override
    public void spawnEntity(Entity parent) {
        Bullet bullet = new Bullet();

        // Set the bullet's properties based on the parent entity
        double changeX = Math.cos(Math.toRadians(parent.getRotation() -90));
        double changeY = Math.sin(Math.toRadians(parent.getRotation() -90));

        bullet.setLocation(new Vector2(parent.getLocation()).add(new Vector2(changeX, changeY).multiply(10))); // Place the bullet 10 ahead of parent
        bullet.getLocation().subtract(Bullet.sizing / 2);

        bullet.getVelocity().add(new Vector2(changeX, changeY)).multiply(5).max(5);

        BulletSP.world.addEntity(bullet);
    }
    
}
