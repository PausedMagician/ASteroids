import PawzdCO.common.services.IEntityProcessingService;

module Asteroid {
    requires common;
    provides IEntityProcessingService with PawzdCO.asteroid.AsteroidProcessor;
    provides PawzdCO.common.services.IGamePlugin with PawzdCO.asteroid.AsteroidPlugin;
}
