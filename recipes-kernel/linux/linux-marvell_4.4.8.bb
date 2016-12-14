require recipes-kernel/linux/linux-marvell.inc

SOC_SRC_URI = "git://git@github.com/MarvellEmbeddedProcessors/linux-marvell.git;protocol=ssh"
SRCBRANCH = "linux-4.4.8"
SRCREV = "bccb35302bb18f66bfd5047c5a58b5ea5f3c1373"

SRC_URI += "${@bb.utils.contains('TUNE_FEATURES', 'bigendian', 'file://big-endian.cfg',  '', d)}"

COMPATIBLE_MACHINE = "(armada38x)"

