package PawzdCO.common.services;

import PawzdCO.common.data.GameData;
import PawzdCO.common.data.World;

public interface IGamePlugin {
    /**
     * This method is called when the plugin is loaded.
     * @param gd
     * @param w
     */
    void start(GameData gd, World w);

    /**
     * This method is called when the plugin is unloaded.
     * @param gd
     * @param w
     */
    void stop(GameData gd, World w);
}
