SUMMARY = "Marvell ODP-MUSDK"
DESCRIPTION = "Marvell ODP-MUSDK (OpenDataPlane over Marvell User Space SDK) \
               implementation for the Armada family drivers based on the \
               standard ODP linux-generic platform with the changes matching \
               the MUSDK architecture"
HOMEPAGE = "https://github.com/MarvellEmbeddedProcessors/odp-marvell"
SECTION = "networking"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ccfa994aa96974cfcd39a59faee20a2"
DEPENDS = "cunit libpcap musdk-marvell openssl"
PROVIDES += "odp"

SRCBRANCH = "odp-armada-17.10"
SRCREV = "d5274f339b9016fbb89694c900c3ce07b75c2977"
SRC_URI = " \
    git://github.com/MarvellEmbeddedProcessors/odp-marvell.git;branch=${SRCBRANCH};protocol=https \
    file://0001-example-slow_path-Makefile.am-Include-helper-directo.patch \
"

S = "${WORKDIR}/git"

inherit autotools-brokensep

EXTRA_OECONF += " \
    --with-platform=linux-musdk \
    --with-sdk-install-path=${STAGING_INCDIR}/musdk \
    --enable-mvpp2-support \
    --with-openssl-path=${STAGING_INCDIR}/openssl \
"

RDEPENDS_${PN} = " \
    bash \
    mvpp2x-sysfs \
"
RRECOMENDS_${PN} = " \
    kernel-module-marvell-musdk-uio \
    kernel-module-marvell-musdk-pp2 \
    kernel-module-marvell-musdk-sam \
"
RPROVIDES_${PN} += "odp"
RREPLACES_${PN} += "odp"
RCONFLICTS_${PN} += "odp"

COMPATIBLE_MACHINE = "(armada70xx|armada80xx)"
