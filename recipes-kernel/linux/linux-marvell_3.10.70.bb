require linux-marvell.inc

# FIXME: This kernel version does not support out-of-tree build 
B = "${S}"

SRCBRANCH_armada38x = "linux-3.10.70-15t1"
SRCREV_armada38x = "c8ae519736bba6de02ace596b438f14d168648ae"
LOCALVERSION_armada38x = "-2015_T1.1p7"

SRC_URI_append_armada38x = " file://enable-ftrace.cfg"
SRC_URI_append_armada38x-be = " file://big-endian.cfg"
