import PawzdCO.common.services.IBulletSPI;

module common_ship {
    requires common;
    exports PawzdCO.commonShip;
    uses IBulletSPI;
}
