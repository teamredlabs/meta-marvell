require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Marvell Embedded Processors"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=089c6275df97d40f52f08aee128a2ad3"

PROVIDES += "u-boot"

SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/u-boot-marvell;branch=${SRCBRANCH};protocol=https \
"

SRCBRANCH = "u-boot-2017.03-armada-17.06"
SRCREV = "a33ecb814b9f603db77e9c338f6ceb015b2e3298"

S = "${WORKDIR}/git"

inherit marvell-u-boot-localversion

EXTRA_OEMAKE += "DEVICE_TREE=${UBOOT_DEVICE_TREE}"

COMPATIBLE_MACHINE = "(armada37xx|armada70xx|armada80xx)"
