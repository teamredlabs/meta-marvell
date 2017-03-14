# Copyright (C) 2016 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS_append = " kern-tools-native xz-native bc-native"
DEPENDS_append_aarch64 = " libgcc"
KERNEL_CC_append_aarch64 = " ${TOOLCHAIN_OPTIONS}"
KERNEL_LD_append_aarch64 = " ${TOOLCHAIN_OPTIONS}"

inherit kernel
inherit marvell-kernel-config-fragments
inherit marvell-kernel-localversion

require recipes-kernel/linux/linux-dtb.inc

# Default repositories for each SoC family
SRC_SRC_URI = "UNDEFINED"
SRCBRANCH = "UNDEFINED"

SRC_URI = "${SOC_SRC_URI};branch=${SRCBRANCH} \
		   file://defconfig \
"

# Put a local version until we have a true SRCREV to point to
LOCALVERSION ?= ""
SCMVERSION ?= "y"
SRCBRANCH ?= ""

S = "${WORKDIR}/git"
