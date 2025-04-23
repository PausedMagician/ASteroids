package PawzdCO.common.services;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;

public interface IEntityPostProcessingService {
    /**
     * This method is called after the entity has been processed.
     * @param world
     * @param gameData
     */
    void process(World world, GameData gameData);
}
