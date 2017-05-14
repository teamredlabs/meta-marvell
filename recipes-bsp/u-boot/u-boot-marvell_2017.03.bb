require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Marvell Embedded Processors"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=9cf65ad0dfdd03102e58510c8a27a71a"

PROVIDES += "u-boot"

SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/u-boot-marvell;branch=${SRCBRANCH};protocol=https \
"

SRCBRANCH = "u-boot-2017.03-armada-17.06"
SRCREV = "5c839036cb5adad33d30bad440d5e12119b73538"

S = "${WORKDIR}/git"

inherit marvell-u-boot-localversion

COMPATIBLE_MACHINE = "(armada37xx|armada70xx|armada80xx)"
