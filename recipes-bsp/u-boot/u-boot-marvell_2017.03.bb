require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot for Marvell Embedded Processors"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=089c6275df97d40f52f08aee128a2ad3"

PROVIDES += "u-boot"
RPROVIDES_${PN} += "u-boot"

DEPENDS_append = " dtc-native"

SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/u-boot-marvell;branch=${SRCBRANCH};protocol=https \
"

SRCBRANCH = "u-boot-2017.03-armada-17.10"
SRCREV = "06ad760c886d4e129536ac0577875bcb0117039c"

S = "${WORKDIR}/git"

do_fix_makefile () {
    sed -i $(grep -n '^EXTRAVERSION =' ${S}/Makefile | awk '{split($0,a,":"); print a[1]}')"s/.*/EXTRAVERSION = -${PR}/" ${S}/Makefile
}

python() {
    bb.build.addtask('do_fix_makefile', 'do_configure', 'do_patch', d)
}

inherit marvell-u-boot-localversion

EXTRA_OEMAKE += "DEVICE_TREE=${UBOOT_DEVICE_TREE}"

COMPATIBLE_MACHINE = "(armada37xx|armada70xx|armada80xx)"
