package PawzdCO.common.services;

import PawzdCO.common.data.Entity;

public interface IBulletSPI {

    /**
     * This method is called to spawn an entity.
     * 
     * @param entity
     */
    void spawnEntity(Entity entity);
    
}
