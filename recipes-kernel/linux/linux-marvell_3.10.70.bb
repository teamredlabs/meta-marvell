require linux-marvell.inc

# FIXME: This kernel version does not support out-of-tree build 
B = "${S}"

SRCBRANCH_armada38x = "linux-3.10.70-15t1"
SRCREV_armada38x = "0696bc19e18f163f5ed70baf13edeeaa4d6ff652"
LOCALVERSION_armada38x = "-2015_T1.1"

SRC_URI_append_armada38x = " file://enable-ftrace.cfg"
SRC_URI_append_armada38x-be = " file://big-endian.cfg"

SRCBRANCH_clearfog = "linux-3.10.70-15t1-clearfog"
SRCREV_clearfog = "902739f3353150ac9eb69ad995098f3079d862a3"
LOCALVERSION_clearfog = "-2015_T1.1"

SRC_URI_append_clearfog = " file://add-clearfog-dts.patch"
