import PawzdCO.common.services.IScoreSPI;

module common {
    requires javafx.base;
    requires transitive javafx.graphics;
    exports PawzdCO.common.data;
    exports PawzdCO.common.services;
    uses IScoreSPI;
}
