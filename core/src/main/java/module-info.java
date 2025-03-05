module Core {
    requires javafx.controls;
    requires javafx.graphics;
    opens PawzdCO.main to javafx.graphics;
    requires common;
    uses PawzdCO.common.services.IGamePlugin;
    uses PawzdCO.common.services.IEntityProcessingService;
    // uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
}


