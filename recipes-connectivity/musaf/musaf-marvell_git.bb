SUMMARY = "Marvell Linux User-Space Application Framework over Marvell ODP and MUSDK"
DESCRIPTION = "Marvell Linux User-Space Application Framework (MUSAF) over Marvell ODP and MUSDK \
               is a set of header files, libraries, scripts, tools, and methodologies used for \
               creation of user space network applications based on Marvell Armada-70x0/80x0 SoCs"
HOMEPAGE = "https://github.com/MarvellEmbeddedProcessors/musaf-marvell"
SECTION = "networking"
# TODO: provide with correct license reference; the license file below is a placeholder
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/nf/route/route.c;beginline=1;endline=31;md5=12b6d6606801abadcb0ef87f1c5a4448"
DEPENDS = "musdk-marvell odp-marvell"
PROVIDES += "musaf"

SRCBRANCH = "musaf-armada-17.10"
SRCREV = "dd3e2ce53fc928d21e3b03bc8d98eeaf0c199d68"
SRC_URI = " \
    git://github.com/MarvellEmbeddedProcessors/musaf-marvell.git;branch=${SRCBRANCH};protocol=https \
    file://configure.ac-Set-correct-CC-LD-AR-and-CPP-to-build-w.patch \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF += " \
    --with-odp-path=${STAGING_DIR_TARGET}/usr \
    --with-musdk-path=${STAGING_DIR_TARGET}/usr \
"

RDEPENDS_${PN} = " \
    musdk \
    odp \
"
RPROVIDES_${PN} += "musaf"
RREPLACES_${PN} += "musaf"
RCONFLICTS_${PN} += "musaf"

COMPATIBLE_MACHINE = "(armada70xx|armada80xx)"
