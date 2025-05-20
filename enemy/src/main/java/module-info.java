import PawzdCO.common.services.IEntityProcessingService;
import PawzdCO.common.services.IGamePlugin;
import PawzdCO.enemy.EnemyControlSystem;
import PawzdCO.enemy.EnemyPluginService;

module enemy {
    requires common;
    requires common_ship;

    provides IGamePlugin with EnemyPluginService;
    provides IEntityProcessingService with EnemyControlSystem;
}
