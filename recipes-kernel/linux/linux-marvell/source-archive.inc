LINUX_VERSION_armada38x = "3.10.70"
MARVELL_LINUX_VERSION = "2015_T1.1"

# Linux BSP (LSP) Source Codes
SRC_URI_armada38x = "https://www.kernel.org/pub/linux/kernel/v3.x/linux-${LINUX_VERSION}.tar.xz \
                     file://linux-${LINUX_VERSION}-custom.patch \
"
SRC_URI[md5sum] = "2c014d383bea74e3f4325255250595c2"
SRC_URI[sha256sum] = "ffd6e409dc4fef1f7bfe3ffba0383511ecdbfe2919527d9241cf640efc2bfb74"

S = "${WORKDIR}/linux-${LINUX_VERSION}/"
