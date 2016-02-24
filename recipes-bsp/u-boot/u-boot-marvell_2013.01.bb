require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Marvell Armada 38x"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=90fb5e9af37e63370e37e9a9178cc427"

PROVIDES += "u-boot"

SRC_URI = "git://git@github.com/MarvellEmbeddedProcessors/u-boot-marvell;branch=${SRCBRANCH};protocol=https \
           file://u-boot-2013.01_hard_vfp.patch \
           file://u-boot-2013.01_default_bootcmd.patch \
"

SRCBRANCH_armada38x = "u-boot-2013.01-15t1"
SRCREV_armada38x = "b21a7137318cdccd1d6569c27dddd33447328770"

SRCBRANCH_clearfog = "u-boot-2013.01-15t1-clearfog"
SRCREV_clearfog = "c1d6f3e8e315c3843147c74013ed915231774a58"

PV .= "+git${SRCPV}"

S = "${WORKDIR}/git"

do_compile () {
	unset LDFLAGS

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
		make ${UBOOT_MARVELL_MACHINE}_config
		make u-boot.mmc
	else
		export CROSS_COMPILE_BH=${TARGET_PREFIX}
		perl build.pl -f spi -b ${UBOOT_MARVELL_MACHINE} -v "yocto" -i spi -c
		cp -f u-boot-a38x-yocto-spi.bin u-boot.bin
	fi
}

COMPATIBLE_MACHINE = "(armada38x)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
