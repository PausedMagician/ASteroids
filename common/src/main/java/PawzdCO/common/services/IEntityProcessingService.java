package PawzdCO.common.services;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;

public interface IEntityProcessingService {
    void process(World world, GameData gameData);
}
