DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.md;md5=829bdeb34c1d9044f393d5a16c068371"

DEPENDS = " \
    ${@bb.utils.contains("MACHINE_FEATURES", "edk-efi", "edk-marvell", "virtual/bootloader",d)} \
"

DEPENDS_append_armada37xx = "lib32-gcc-cross-arm unzip-native"

PROVIDES += "arm-trusted-firmware"

inherit deploy

S = "${WORKDIR}/atf"

SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/atf-marvell.git;branch=${SRCBRANCH_atf};protocol=https;name=atf;destsuffix=atf \
    git://git@github.com/MarvellEmbeddedProcessors/mv-ddr-marvell.git;branch=${SRCBRANCH_mv-ddr};protocol=https;name=mv-ddr;destsuffix=mv-ddr \
    git://git@github.com/MarvellEmbeddedProcessors/binaries-marvell.git;branch=${SRCBRANCH_bin-marvell};protocol=ssh;name=bin-marvell;destsuffix=bin-marvell \
"

SRCREV_atf = "66befb58cd4ee488b121fb65fee9d7d750e34337"
SRCBRANCH_atf = "atf-v1.3-armada-17.06"

SRCREV_mv-ddr = "a55312d36ebf97710bcdcba6f12fd0a71b37ce76"
SRCBRANCH_mv-ddr = "mv_ddr-armada-17.06"

SRCREV_bin-marvell = "dcca74278b7f2f0313775cd6d1983190116242fd"
SRCBRANCH_bin-marvell = "binaries-marvell-armada-17.06"

SRCREV_FORMAT = "atf"

PV .= "+git${SRCPV}"

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Avoid messing up with build flags
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_configure[noexec] = "1"

python() {
    atfplatform = d.getVar("ARM_TRUSTED_FIRMWARE_PLATFORM", False)
    if not atfplatform:
        raise bb.parse.SkipPackage("ARM_TRUSTED_FIRMWARE_PLATFORM must be " \
                                   "set in the %s machine configuration." \
                                   % d.getVar("MACHINE", True))
}

python() {
    machine_features = d.getVar("MACHINE_FEATURES", True).split()
    if "edk-efi" in machine_features:
        d.setVar("BL33_IMAGE", "edk-efi.fd")
    else:
        d.setVar("BL33_IMAGE", "u-boot.bin")
}

EXTRA_OEMAKE += " \
    BL33=${DEPLOY_DIR_IMAGE}/${BL33_IMAGE} \
    PLAT=${ARM_TRUSTED_FIRMWARE_PLATFORM} \
    MV_DDR_PATH=${WORKDIR}/mv-ddr \
    USE_COHERENT_MEM=0 \
    LOG_LEVEL=20 \
"

# ARM 32-bit cross compiler, which is required by building WTMI image for CM3.
export CROSS_CM3 = "${STAGING_BINDIR_NATIVE}/arm-marvellmllib32-linux-gnueabi/arm-marvellmllib32-linux-gnueabi-"

# Export path to binary file
export SCP_BL2="${WORKDIR}/bin-marvell/RTOSDemo-cm3.bin"

do_compile_prepend_armada37xx() {
    if [ ! -f ${S}/WTPTP_TOOLS_3_3_12/Linux_Tools/*tbb_linux.exe ]; then
        unzip ${S}/tools/wtp/WTPTP_TOOLS_3_3_12.zip
        chmod a+x ${S}/WTPTP_TOOLS_3_3_12/Linux_Tools/*tbb_linux.exe
        cp ${S}/WTPTP_TOOLS_3_3_12/Linux_Tools/*tbb_linux.exe ${STAGING_BINDIR_NATIVE}/
   fi

}

do_compile() {
	oe_runmake all fip
}

do_install[noexec] = "1"

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/build/${ARM_TRUSTED_FIRMWARE_PLATFORM}/release/flash-image.bin ${DEPLOYDIR}/flash-image.bin-${MACHINE}-${PV}-${PR}
	ln -sf flash-image.bin-${MACHINE}-${PV}-${PR} ${DEPLOYDIR}/flash-image.bin-${MACHINE}
	ln -sf flash-image.bin-${MACHINE}-${PV}-${PR} ${DEPLOYDIR}/flash-image.bin
}
addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(armada70xx|armada80xx)"
