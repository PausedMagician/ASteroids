import PawzdCO.asteroid.AsteroidPlugin;
import PawzdCO.common.services.IGamePlugin;

module asteroid2 {
    requires common;

    provides IGamePlugin with AsteroidPlugin;
}
