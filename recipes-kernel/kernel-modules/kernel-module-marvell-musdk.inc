SUMMARY = "MUSDK kernel modules"

FILESEXTRAPATHS_prepend := "${THISDIR}/../../recipes-musdk/musdk-marvell/musdk-marvell/:"

require recipes-musdk/musdk-marvell/musdk-marvell.inc

DEPENDS = "virtual/kernel"
do_configure[depends] += "virtual/kernel:do_shared_workdir"
do_populate_sysroot_setscene[depends] += "virtual/kernel:do_shared_workdir"

inherit module

export KDIR = "${STAGING_KERNEL_BUILDDIR}"

COMPATIBLE_MACHINE = "(armada37xx|armada70xx|armada80xx)"
