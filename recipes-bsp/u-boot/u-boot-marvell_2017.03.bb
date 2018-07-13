require u-boot-common-marvell_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

SUMMARY = "U-Boot for Marvell Embedded Processors"
DEPENDS += "dtc-native"

PROVIDES += "u-boot"
RPROVIDES_${PN} += "u-boot"
