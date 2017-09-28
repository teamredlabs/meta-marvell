DESCRIPTION = "OpenDataPlane (ODP) provides a data plane application \
programming environment that is easy to use, high performance, and portable \
between networking SoCs."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ccfa994aa96974cfcd39a59faee20a2"

DEPENDS = "cunit libpcap netmap-marvell openssl"
do_configure[depends] += "netmap-marvell:do_populate_sysroot"

SRCBRANCH = "odp-armada-17.10"
SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/odp-marvell.git;branch=${SRCBRANCH};protocol=https \
    file://0001-example-slow_path-Makefile.am-Include-helper-directo.patch \
"

SRCREV = "d5274f339b9016fbb89694c900c3ce07b75c2977"

S = "${WORKDIR}/git"

inherit autotools

DISABLE_STATIC = ""

# We need to pass netmap-marvell directory path.
EXTRA_OECONF += " \
    --with-netmap-path=${STAGING_INCDIR}/net \
"

RDEPENDS_${PN} = "bash libcrypto"

COMPATIBLE_MACHINE = "(armada70xx|armada80xx)"

