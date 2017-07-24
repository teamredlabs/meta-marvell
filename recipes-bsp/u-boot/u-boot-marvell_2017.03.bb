require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Marvell Embedded Processors"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=089c6275df97d40f52f08aee128a2ad3"

PROVIDES += "u-boot"

DEPENDS_append = " dtc-native"

SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/u-boot-marvell;branch=${SRCBRANCH};protocol=https \
"

SRCBRANCH = "u-boot-2017.03-armada-17.08"
SRCREV = "5d1a40f36f00a65919dce43b7210b98c47dea96a"

S = "${WORKDIR}/git"

inherit marvell-u-boot-localversion

EXTRA_OEMAKE += "DEVICE_TREE=${UBOOT_DEVICE_TREE}"

COMPATIBLE_MACHINE = "(armada37xx|armada70xx|armada80xx)"
