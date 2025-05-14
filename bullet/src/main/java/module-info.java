import PawzdCO.bullet.BulletProcessingService;
import PawzdCO.bullet.BulletSP;
import PawzdCO.common.services.IBulletSPI;
import PawzdCO.common.services.IEntityProcessingService;
import PawzdCO.common.services.IWorldAware;

module bullet {
    requires common;
    provides IEntityProcessingService with BulletProcessingService;
    provides IBulletSPI with BulletSP;
    provides IWorldAware with BulletSP;
}
