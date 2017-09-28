SUMMARY = "Marvell PPv2 sysfs commands"
DESCRIPTION= "Marvell sysfs private implementation for PPv2 packet processor"
SECTION = "networking"
HOMEPAGE = "https://github.com/MarvellEmbeddedProcessors/mvpp2x-marvell"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://mv_pp2x_main.c;beginline=1;endline=17;md5=118e064612a197e129488f096ad65ae5"

SRCBRANCH = "mvpp2x-armada-17.10"
SRCREV = "3cb337f10f4d3a97ead651336f7e6aefd0a7523c"
SRC_URI = "git://git@github.com/MarvellEmbeddedProcessors/mvpp2x-marvell.git;branch=${SRCBRANCH};protocol=https"
SRC_URI[md5sum] = "91c268de0dda9498a02d0cb50986c300"
SRC_URI[sha256sum] = "ecc8ee4ef5032b94031620bd7f15008deceb8653d278e6f7345c98773a8dc7cb"

PV = "master+git${SRCPV}"

S = "${WORKDIR}/git"

RPROVIDES_${PN} += "mvpp2x-sysfs"
RREPLACES_${PN} += "mvpp2x-sysfs"
RCONFLICTS_${PN} += "mvpp2x-sysfs"

inherit module

export KDIR = "${KERNEL_SRC}"

COMPATIBLE_MACHINE = "(armada70xx|armada80xx)"

LDFLAGS := "${@'${LDFLAGS}'.replace('-Wl,-O1', '')}"
LDFLAGS := "${@'${LDFLAGS}'.replace('-Wl,--as-needed', '')}"
LDFLAGS := "${@'${LDFLAGS}'.replace('-Wl,--hash-style=gnu', '')}"

do_configure () {
    cd ${S}/sysfs
    cp -rf Makefile_sysfs Makefile
}

do_compile () {
    cd ${S}/sysfs
    oe_runmake clean
    oe_runmake KERNEL_SOURCES=1
}

do_install () {
    install -Dm 0644 ${S}/sysfs/mvpp2x_sysfs.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/mvpp2x_sysfs/mvpp2x_sysfs.ko
}

KERNEL_MODULE_AUTOLOAD = "mvpp2x-sysfs"
