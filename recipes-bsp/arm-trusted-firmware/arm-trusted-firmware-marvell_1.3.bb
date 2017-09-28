require recipes-bsp/arm-trusted-firmware/arm-trusted-firmware-marvell.inc


SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/atf-marvell.git;branch=${SRCBRANCH_atf};protocol=https;name=atf;destsuffix=atf \
    git://git@github.com/MarvellEmbeddedProcessors/mv-ddr-marvell.git;branch=${SRCBRANCH_mv-ddr};protocol=https;name=mv-ddr;destsuffix=mv-ddr \
    git://git@github.com/MarvellEmbeddedProcessors/binaries-marvell.git;branch=${SRCBRANCH_bin-marvell};protocol=https;name=bin-marvell;destsuffix=bin-marvell \
    git://git@github.com/MarvellEmbeddedProcessors/A3700-utils-marvell.git;branch=${SRCBRANCH_A3700-utils};protocol=https;name=A3700-utils;destsuffix=A3700-utils \
    file://Use-BUILD_CC-to-compile-native-parts.patch \
"

SRCREV_atf = "13f2984f0af30776eb71938bcdf06b971297cdad"
SRCBRANCH_atf = "atf-v1.3-armada-17.10"

SRCREV_mv-ddr = "24dda1fb8278aa8abca69197bfea3a2bbb163494"
SRCBRANCH_mv-ddr = "mv_ddr-armada-17.10"

SRCREV_bin-marvell = "0dabe23b956420b0928d337d635f0cd5646c33d0"
SRCBRANCH_bin-marvell = "binaries-marvell-armada-17.10"

SRCREV_A3700-utils = "af63f95009b516718be0fd0499701e8609f6bda9"
SRCBRANCH_A3700-utils = "A3700_utils-armada-17.10"

# Export path to a70xx binary file
do_compile_prepend_armada70xx() {
    export SCP_BL2="${WORKDIR}/bin-marvell/mrvl_scp_bl2_7040.img"
}

# Export path to a80xx binary file
do_compile_prepend_armada80xx() {
    export SCP_BL2="${WORKDIR}/bin-marvell/mrvl_scp_bl2_8040.img"
}
