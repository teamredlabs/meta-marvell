DESCRIPTION = "EDK II Marvell"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://OpenPlatformPkg/License.txt;md5=323ef31bed34c64980e68243b53e40e2"

DEPENDS = "ossp-uuid-native iasl-native"

inherit deploy

S = "${WORKDIR}/git"

PV .= "+git${SRCPV}"

export CC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"
export CXX="${BUILD_CXX} ${BUILD_CXXFLAGS} ${BUILD_LDFLAGS}"
export STAGING_INCDIR_NATIVE
export STAGING_LIBDIR_NATIVE

# Toolchain path
export GCC49_AARCH64_PREFIX="${TARGET_PREFIX}"

do_configure[noexec] = "1"

python() {
    edkplatform = d.getVar("EDK_PLATFORM", False)
    if not edkplatform:
        raise bb.parse.SkipPackage("EDK_PLATFORM must be set in the" \
                                   " %s machine configuration." \
                                   % d.getVar("MACHINE", True))
}

do_compile() {
    . ${S}/edksetup.sh
    make -C BaseTools
    build -a AARCH64 -t GCC49 -b DEBUG -p OpenPlatformPkg/Platforms/Marvell/Armada/${EDK_PLATFORM}.dsc
}

do_install[noexec] = "1"

do_deploy () {
    install -d ${DEPLOYDIR}
    install -m 0644 ${S}/Build/${EDK_PLATFORM}-AARCH64/DEBUG_GCC49/FV/*.fd ${DEPLOYDIR}/edk-efi.fd-${MACHINE}-${PV}-${PR}
    ln -sf edk-efi.fd-${MACHINE}-${PV}-${PR} ${DEPLOYDIR}/edk-efi.fd-${MACHINE}
    ln -sf edk-efi.fd-${MACHINE}-${PV}-${PR} ${DEPLOYDIR}/edk-efi.fd

    install -Dm 0755 ${WORKDIR}/startup.nsh ${DEPLOYDIR}/startup.nsh-${MACHINE}-${PV}-${PR}
    ln -sf startup.nsh-${MACHINE}-${PV}-${PR} ${DEPLOYDIR}/startup.nsh-${MACHINE}
    ln -sf startup.nsh-${MACHINE}-${PV}-${PR} ${DEPLOYDIR}/startup.nsh
}

addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(armada70xx|armada80xx)"
