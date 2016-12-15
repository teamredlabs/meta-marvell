require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Marvell Armada 38x"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=90fb5e9af37e63370e37e9a9178cc427"

PROVIDES += "u-boot"

SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/u-boot-marvell;branch=${SRCBRANCH};protocol=https \
    file://0001-armv7-config.mk-Enable-hard-float-support.patch \
    file://0002-Revert-bin_header_debug-add-implementation-of-C-libr.patch \
"

SRCBRANCH = "u-boot-2013.01"
SRCREV = "45fabe89310db3d8b3821bea55f84defe8f815c1"

PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"

do_compile () {
    unset LDFLAGS
    unset CFLAGS
    unset CPPFLAGS

    export CROSS_COMPILE=${TARGET_PREFIX}
    export CROSS_COMPILE_BH=${TARGET_PREFIX}
    export USE_PRIVATE_LIBGCC=${STAGING_LIBDIR}/${TARGET_SYS}/`$CC -dumpversion`

    if [ ! -e ${B}/.scmversion -a ! -e ${S}/.scmversion ]
    then
        echo ${UBOOT_LOCALVERSION} > ${B}/.scmversion
        echo ${UBOOT_LOCALVERSION} > ${S}/.scmversion
    fi

    export CROSS_COMPILE=${TARGET_PREFIX}

    if [ "${UBOOT_MARVELL_MACHINE}" = "armada_38x_clearfog" ]
    then
        export CROSS_COMPILE_BH=${TARGET_PREFIX}
        perl build.pl -f mmc -b ${UBOOT_MARVELL_MACHINE} -v "yocto" -c -d 3 -m 3
        cp -f u-boot-a38x-yocto-mmc.bin u-boot.mmc
    else
        export CROSS_COMPILE_BH=${TARGET_PREFIX}
        perl build.pl -f spi -b ${UBOOT_MARVELL_MACHINE} -v "yocto" -i spi -c -d 3 -m 3
        cp -f u-boot-a38x-yocto-spi.bin u-boot.bin
    fi
}

COMPATIBLE_MACHINE = "(armada38x)"

PACKAGE_ARCH = "${MACHINE_ARCH}"

