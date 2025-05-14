package PawzdCO.common.services;

import PawzdCO.common.data.World;

public interface IWorldAware {
    
    /**
     * This method is called to provide the world so the implementation can
     * spawn entities.
     * 
     * @param world
     */
    void provideWorld(World world);

}
