require recipes-kernel/linux/linux-marvell.inc

SOC_SRC_URI = "git://git@github.com/MarvellEmbeddedProcessors/linux-marvell.git;protocol=https"
SRCBRANCH = "linux-4.4.52-armada-17.06"
SRCREV = "10a2c3eb3618e67cc2dafa88b2ebbbca8bdb660a"

SRC_URI += "${@bb.utils.contains('TUNE_FEATURES', 'bigendian', 'file://big-endian.cfg',  '', d)}"

SRC_URI_append_clearfog = " \
    file://iptables.cfg \
"

COMPATIBLE_MACHINE = "(armada37xx|armada38x|armada70xx|armada80xx)"
