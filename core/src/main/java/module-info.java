import PawzdCO.common.services.*;

module Core {
    requires transitive common;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires spring.core;
    requires spring.context;
    requires spring.beans;

    // opens PawzdCO.main to javafx.graphics;
    exports PawzdCO.main;
    opens PawzdCO.main to javafx.graphics,spring.core;
    uses IGamePlugin;
    uses IEntityProcessingService;
    uses IEntityPostProcessingService;
    uses IWorldAware;
}


