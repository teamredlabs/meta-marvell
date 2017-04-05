require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Marvell Embedded Processors"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=9cf65ad0dfdd03102e58510c8a27a71a"

DEPENDS_append = " dtc-native"

PROVIDES += "u-boot"

SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/u-boot-marvell;branch=${SRCBRANCH};protocol=https \
    file://0001-env-vars-Configure-to-use-NFS-boot.patch \
    file://0002-configs-mvebu-Set-enviroment-to-boot-with-MMC.patch \
    file://0003-drivers-net-e1000-Fix-indentation.patch \
    file://0004-Fix-the-compile-issue-under-gcc6.patch \
    file://0005-Makefile-Use-dtc-from-host.patch \
"

SRCBRANCH = "u-boot-2015.01-armada-17.02"
SRCREV = "82f77b5080017fc84890a2a612d8e9ac835bd6ca"

S = "${WORKDIR}/git"

inherit marvell-u-boot-localversion

COMPATIBLE_MACHINE = "(armada37xx|armada70xx|armada80xx)"
