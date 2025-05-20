import PawzdCO.common.services.*;
import PawzdCO.player.*;

module player {
    requires common;
    requires common_ship;

    provides IGamePlugin with PlayerPluginService;
    provides IEntityProcessingService with PlayerControlSystem;
    uses IBulletSPI;
}
