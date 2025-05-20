package PawzdCO.common.services;

import PawzdCO.common.data.Entity;

public interface IAsteroidSPI {
    
    /**
     * This method is called to spawn an entity.
     * 
     * @param entity
     */
    void spawnEntity(Entity entity);

}
