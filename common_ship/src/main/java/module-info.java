import PawzdCO.common.services.IBulletSPI;

module common_ship {
    requires transitive common;
    exports PawzdCO.commonShip;
    uses IBulletSPI;
}
