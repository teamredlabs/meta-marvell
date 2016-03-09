SUMMARY = "mv_cesa_tool: Used to test Marvell CESA"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit module

DEPENDS = "virtual/kernel"
do_configure[depends] += "virtual/kernel:do_populate_sysroot"

SRC_URI = "file://Makefile \
           file://COPYING \
          "

export TOOLCHAIN_OPTIONS
export HOST_CC_ARCH

S = "${WORKDIR}"

FILES_${PN} += "${bindir}/mv_cesa_tool"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
