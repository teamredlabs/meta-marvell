require netmap-marvell.inc

PROVIDES += "netmap"

DEPENDS = "netmap-marvell-modules"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI += "file://makefile_fixup.patch"

EXTRA_OECONF = " \
    --kernel-dir=${STAGING_KERNEL_BUILDDIR} \
    --kernel-sources=${STAGING_KERNEL_DIR} \
    --no-drivers \
    --disable-generic \
    --prefix=${prefix} \
    --destdir=${D} \
    --cc='${CC}' \
    --ld='${LD}' \
"

do_configure () {
    cd ${S}/LINUX
    ./configure ${EXTRA_OECONF}
}

do_compile () {
    cd ${S}/LINUX
    make apps
}

do_install () {
    cd ${S}/LINUX
    make install-apps DESTDIR=${D}
    cp -r ${S}/sys ${D}${includedir}
}

FILES_${PN} += "${bindir}"
RDEPENDS_${PN} = "\
    kernel-module-netmap \
    netmap-marvell-modules \
"

RPROVIDES_${PN} += "netmap"
RREPLACES_${PN} += "netmap"
RCONFLICTS_${PN} += "netmap"
