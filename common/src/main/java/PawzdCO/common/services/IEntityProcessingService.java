package PawzdCO.common.services;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;

public interface IEntityProcessingService {
    /**
     * This method is called to process the entity.
     * @param world
     * @param gameData
     */
    void process(World world, GameData gameData);
}
