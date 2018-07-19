require recipes-bsp/arm-trusted-firmware/arm-trusted-firmware-marvell.inc

SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/atf-marvell.git;branch=${SRCBRANCH_atf};protocol=https;name=atf;destsuffix=atf \
    git://git@github.com/MarvellEmbeddedProcessors/mv-ddr-marvell.git;branch=${SRCBRANCH_mv-ddr};protocol=https;name=mv-ddr;destsuffix=mv-ddr \
    git://git@github.com/MarvellEmbeddedProcessors/binaries-marvell.git;branch=${SRCBRANCH_bin-marvell};protocol=https;name=bin-marvell;destsuffix=bin-marvell \
    git://git@github.com/MarvellEmbeddedProcessors/A3700-utils-marvell.git;branch=${SRCBRANCH_A3700-utils};protocol=https;name=A3700-utils;destsuffix=A3700-utils \
    file://Use-BUILD_CC-to-compile-native-parts.mpatch \
    file://Provided-include-directory-to-wtmi_ddr-Makefile.mpatch \
    file://parser-add-preset_ddr_conf-field-for-preset-ddr-sett.mpatch \
    file://ddr-update-ddr-topology-for-ddr3-2gbytes.mpatch \
"

SRCREV_atf = "34247e027e234634d65287d1cfdf0c3d6eb98cae"
SRCBRANCH_atf = "atf-v1.3-armada-17.10"

SRCREV_mv-ddr = "f537e1d8f0acbececf10a6c15b1d5072f388c863"
SRCBRANCH_mv-ddr = "mv_ddr-armada-17.10"

SRCREV_bin-marvell = "15682d46e0cd849f3c8e850c3bfc179be9c9dffc"
SRCBRANCH_bin-marvell = "binaries-marvell-armada-17.10"

SRCREV_A3700-utils = "177ba4771f3011362793422d0edc7f9b77d9424d"
SRCBRANCH_A3700-utils = "A3700_utils-armada-17.10"

do_patch() {
    cd ${WORKDIR}/atf
    git am ../Use-BUILD_CC-to-compile-native-parts.mpatch
    cd ${WORKDIR}/A3700-utils
    git am ../Provided-include-directory-to-wtmi_ddr-Makefile.mpatch
    git am ../parser-add-preset_ddr_conf-field-for-preset-ddr-sett.mpatch
    git am ../ddr-update-ddr-topology-for-ddr3-2gbytes.mpatch
    cd ${WORKDIR}
}

# Export path to a70xx binary file
do_compile_prepend_armada70xx() {
    export SCP_BL2="${WORKDIR}/bin-marvell/mrvl_scp_bl2_7040.img"
}

# Export path to a80xx binary file
do_compile_prepend_armada80xx() {
    export SCP_BL2="${WORKDIR}/bin-marvell/mrvl_scp_bl2_8040.img"
}
