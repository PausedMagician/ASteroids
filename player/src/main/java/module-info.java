import PawzdCO.common.services.IEntityProcessingService;
import PawzdCO.common.services.IGamePlugin;

module player {
    requires common;
    requires javafx.graphics;
    provides IEntityProcessingService with PawzdCO.player.Player;
    provides IGamePlugin with PawzdCO.player.Player;
}
