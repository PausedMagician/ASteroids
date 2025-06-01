import PawzdCO.common.services.*;

module Core {
    requires javafx.controls;
    requires javafx.graphics;
    // opens PawzdCO.main to javafx.graphics.spring.core;


    opens PawzdCO.main to javafx.graphics;
    requires common;
    uses IGamePlugin;
    uses IEntityProcessingService;
    uses IEntityPostProcessingService;
    uses IWorldAware;
}


