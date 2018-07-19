DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.md;md5=829bdeb34c1d9044f393d5a16c068371"

PROVIDES += "arm-trusted-firmware"

DEPENDS = " \
    openssl-native coreutils-native \
    ${@bb.utils.contains("MACHINE_FEATURES", "edk-efi", "edk", "virtual/bootloader",d)} \
"
DEPENDS_append_armada37xx = " \
    lib32-gcc-cross-arm \
    unzip-native \
"

inherit deploy

S = "${WORKDIR}/atf"
SRCREV_FORMAT = "atf"
PV .= "+git${SRCPV}"

export CROSS_COMPILE="${TARGET_PREFIX}"
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

TARGET_VENDOR_CM3 = "${TARGET_VENDOR_virtclass-multilib-lib32}"
TARGET_ARCH_CM3 = "${TUNE_ARCH_32}"
TARGET_OS_CM3 = "${TARGET_OS}-gnueabi"
MACHINE_ARCH_CM3 = "${MACHINE_ARCH}"

TARGET_SYS_CM3 = "${TARGET_ARCH_CM3}"
TARGET_SYS_CM3_append = "${TARGET_VENDOR_CM3}"
TARGET_SYS_CM3_append = "-${TARGET_OS_CM3}"

MULTIMACH_TARGET_SYS_CM3 = "${MACHINE_ARCH_CM3}"
MULTIMACH_TARGET_SYS_CM3_append = "${TARGET_VENDOR_CM3}"
MULTIMACH_TARGET_SYS_CM3_append = "-${TARGET_OS_CM3}"

WORKDIR_CM3 = "${BASE_WORKDIR}/"
WORKDIR_CM3_append = "${MULTIMACH_TARGET_SYS_CM3}/"
WORKDIR_CM3_append = "${PN}/${PV}-${PR}"

STAGING_DIR_TARGET_CM3 = "${WORKDIR_CM3}/lib32-recipe-sysroot"
STAGING_INCDIR_CM3 = "${STAGING_DIR_TARGET_CM3}/usr/include"

export CROSS_CM3 = "${STAGING_BINDIR_NATIVE}/${TARGET_SYS_CM3}/${TARGET_SYS_CM3}-"
export INCLUDE_CM3 = "${STAGING_INCDIR_CM3}"

EXTRA_OEMAKE += " \
    BL33=${DEPLOY_DIR_IMAGE}/${BL33_IMAGE} \
    PLAT=${ARM_TRUSTED_FIRMWARE_PLATFORM} \
    USE_COHERENT_MEM=0 \
    LOG_LEVEL=20 \
    DEBUG=0 \
    SECURE=1 \
"

python() {
    extra_oemake = d.getVar("EXTRA_OEMAKE", True).split()
    
    if "armada37xx" in d.getVar("MACHINEOVERRIDES", True).split(":"):
        option = "DDR_TOPOLOGY"
        variable = "MARVELL_ATF_%s" % option
        settings = {"0": "DDR3 1CS: DB-88F3720-DDR3-Modular (512MB); EspressoBIN (512MB)",
                    "1": "DDR4 1CS: DB-88F3720-DDR4-Modular (512MB)",
                    "2": "DDR3 2CS: EspressoBIN (1GB)",
                    "3": "DDR4 2CS: DB-88F3720-DDR4-Modular (4GB)",
                    "4": "DDR3 1CS: DB-88F3720-DDR3-Modular (1GB)"}
        try:
            setting = d.getVar(variable, True)
            assert setting in settings
            extra_oemake.append("%s=%s" % (option, setting))
        except:
            message = ["%s variable must be set to either one of the following:" % variable]
            for setting, description in sorted(settings.items()):
                message.append("    %s: %s" % (setting, description))
            bb.fatal("\n".join(message))

        option = "CLOCKSPRESET"
        variable = "MARVELL_ATF_%s" % option
        settings = {"CPU_600_DDR_600": "CPU at 600 MHz, DDR at 600 MHz",
                    "CPU_800_DDR_800": "CPU at 800 MHz, DDR at 800 MHz",
                    "CPU_1000_DDR_800": "CPU at 1000 MHz, DDR at 800 MHz",
                    "CPU_1200_DDR_750": "CPU at 1200 MHz, DDR at 750 MHz"}
        try:
            setting = d.getVar(variable, True)
            assert setting in settings
            extra_oemake.append("%s=%s" % (option, setting))
        except:
            message = ["%s variable must be set to either one of the following:" % variable]
            for setting, description in sorted(settings.items()):
                message.append("    %s: %s" % (setting, description))
            bb.fatal("\n".join(message))

        option = "BOOTDEV"
        variable = "MARVELL_ATF_%s" % option
        settings = {"SPINOR": "SPI NOR flash boot",
                    "SPINAND": "SPI NAND flash boot",
                    "EMMCNORM": "eMMC Download Mode",
                    "SATA": "SATA device boot"}
        try:
            setting = d.getVar(variable, True)
            assert setting in settings
            extra_oemake.append("%s=%s" % (option, setting))
        except:
            message = ["%s variable must be set to either one of the following:" % variable]
            for setting, description in sorted(settings.items()):
                message.append("    %s: %s" % (setting, description))
            bb.fatal("\n".join(message))

        option = "PARTNUM"
        variable = "MARVELL_ATF_%s" % option
        try:
            setting = int(d.getVar(variable, True))
            extra_oemake.append("%s=%s" % (option, setting))
        except:
            bb.fatal("%s variable must be set the the "
                     "proper boot partition number of "
                     "the specified boot device" % variable)
    
    d.setVar("EXTRA_OEMAKE", " ".join(extra_oemake))
}

do_compile_prepend_armada37xx() {
    export WTP="${WORKDIR}/A3700-utils"
    export MV_DDR_PATH="${WORKDIR}/mv-ddr"
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
COMPATIBLE_MACHINE = "(armada70xx|armada80xx|armada37xx)"
