import PawzdCO.collision.Collision;
import PawzdCO.common.services.IEntityPostProcessingService;

module collision {
    requires common;
    
    provides IEntityPostProcessingService with Collision;
}
