import PawzdCO.asteroid.AsteroidPlugin;
import PawzdCO.asteroid.AsteroidProcesser;
import PawzdCO.common.services.IAsteroidSPI;
import PawzdCO.common.services.IEntityProcessingService;
import PawzdCO.common.services.IGamePlugin;

module asteroid {
    requires transitive common;
    
    provides IGamePlugin with AsteroidPlugin;
    exports PawzdCO.asteroid;
    uses IAsteroidSPI;
    provides IEntityProcessingService with AsteroidProcesser;
}
