import PawzdCO.asteroid_splitter.AsteroidSplitter;
import PawzdCO.common.services.IAsteroidSPI;
import PawzdCO.common.services.IWorldAware;

module asteroid_splitter {
    requires common;

    provides IAsteroidSPI with AsteroidSplitter;
    provides IWorldAware with AsteroidSplitter;
}
