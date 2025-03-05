import PawzdCO.common.services.IEntityPostProcessingService;

module collision {
    requires common;
    requires javafx.graphics;
    provides IEntityPostProcessingService with PawzdCO.collision.Collision;
}
