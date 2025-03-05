package PawzdCO.common.services;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;

public interface IGamePlugin {
    void start(GameData gd, World w);
    void stop(GameData gd, World w);
}
