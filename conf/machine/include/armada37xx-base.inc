# Provides the Marvell Armada 37xx common settings

require conf/machine/include/arm/arch-arm64.inc

require conf/multilib.conf
MULTILIBS = "multilib:lib32"
DEFAULTTUNE_virtclass-multilib-lib32 = "armv8a-neon"

MACHINE_ENDIANNESS ?= "le"

DEFAULTTUNE_armada37xx ?= "${@bb.utils.contains("MACHINE_ENDIANNESS", "le", "aarch64", "aarch64_be", d)}"

MACHINEOVERRIDES =. "armada37xx:"

PREFERRED_PROVIDER_u-boot-script ??= "u-boot-script-marvell"

IMAGE_FSTYPES += "tar.bz2"

SERIAL_CONSOLE = "115200 ttyMV0"

PREFERRED_PROVIDER_virtual/kernel ??= "linux-marvell"
KERNEL_IMAGETYPE = "Image"

MACHINE_FEATURES = "ext2 ipsec nfs pci smbfs usbgadget usbhost vfat"
