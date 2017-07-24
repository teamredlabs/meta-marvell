require recipes-kernel/linux/linux-marvell.inc

SOC_SRC_URI = "git://git@github.com/MarvellEmbeddedProcessors/linux-marvell.git;protocol=https"
SRCBRANCH = "linux-4.4.52-armada-17.08"
SRCREV = "317abbbecde93068a0e4694428e2a2e6e7b798cb"

SRC_URI += "${@bb.utils.contains('TUNE_FEATURES', 'bigendian', 'file://big-endian.cfg',  '', d)}"

SRC_URI_append_clearfog = " \
    file://iptables.cfg \
"

COMPATIBLE_MACHINE = "(armada37xx|armada38x|armada70xx|armada80xx)"
