SUMMARY = "MUSDK"
DESCRIPTION = "Marvell User Space SDK."
SECTION = "networking"
HOMEPAGE = "https://github.com/MarvellEmbeddedProcessors/musdk-marvell"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRCBRANCH = "musdk-armada-17.10"
SRCREV = "d56bbe0d5abe3ad41d2c015f311b0cbb823a9fdc"
SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/musdk-marvell.git;branch=${SRCBRANCH};protocol=https \
    file://configure.ac-Set-correct-CC-LD-and-AR-to-build-with-.patch \
"


PV = "master+git${SRCPV}"

S = "${WORKDIR}/git"
