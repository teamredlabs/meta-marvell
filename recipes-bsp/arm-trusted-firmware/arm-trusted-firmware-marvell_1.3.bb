require recipes-bsp/arm-trusted-firmware/arm-trusted-firmware-marvell.inc


SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/atf-marvell.git;branch=${SRCBRANCH_atf};protocol=https;name=atf;destsuffix=atf \
    git://git@github.com/MarvellEmbeddedProcessors/mv-ddr-marvell.git;branch=${SRCBRANCH_mv-ddr};protocol=https;name=mv-ddr;destsuffix=mv-ddr \
    git://git@github.com/MarvellEmbeddedProcessors/binaries-marvell.git;branch=${SRCBRANCH_bin-marvell};protocol=ssh;name=bin-marvell;destsuffix=bin-marvell \
    git://git@github.com/MarvellEmbeddedProcessors/A3700-utils-marvell.git;branch=${SRCBRANCH_A3700-utils};protocol=ssh;name=A3700-utils;destsuffix=A3700-utils \
"

SRCREV_atf = "5bd9bc5c49a836d6581e345132d9b16b9375ac21"
SRCBRANCH_atf = "atf-v1.3-armada-17.08"

SRCREV_mv-ddr = "7ffc0a58074b6325b87c45bd67c445f0988b092f"
SRCBRANCH_mv-ddr = "mv_ddr-armada-17.08"

SRCREV_bin-marvell = "98dd008d33bbcc041dde03d12dfd09a73e60a3f3"
SRCBRANCH_bin-marvell = "binaries-marvell-armada-17.08"

SRCREV_A3700-utils = "4425a6b787c50e156b926560ce285dd34745fb30"
SRCBRANCH_A3700-utils = "A3700_utils-armada-17.08"
