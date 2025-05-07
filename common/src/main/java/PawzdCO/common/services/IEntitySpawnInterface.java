package PawzdCO.common.services;

import PawzdCO.common.data.Entity;

public interface IEntitySpawnInterface {
    /**
     * This method is called to spawn an entity.
     * 
     * @param entity
     * @param world
     */
    void spawnEntity(Entity entity, Object world);
    
}
