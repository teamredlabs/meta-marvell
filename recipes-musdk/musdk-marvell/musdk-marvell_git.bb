require recipes-musdk/musdk-marvell/musdk-marvell.inc

PROVIDES += "musdk"

S = "${WORKDIR}/git"

inherit autotools-brokensep

EXTRA_OECONF += " \
    --enable-sam \
    --enable-sam-statistics \
    --enable-sam-debug \
"

RPROVIDES_${PN} += "musdk"
RREPLACES_${PN} += "musdk"
RCONFLICTS_${PN} += "musdk"

RDEPENDS_${PN} += " \
    kernel-module-marvell-musdk-dmax2 \
    kernel-module-marvell-musdk-pp2 \
    kernel-module-marvell-musdk-sam \
    kernel-module-marvell-musdk-uio \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_HOST = '(x86_64|i.86|mips|arm|aarch64).*-linux'
