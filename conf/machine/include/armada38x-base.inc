# Provides the Marvell Armada 38x common settings

require conf/machine/include/tune-cortexa9.inc

MACHINE_ENDIANNESS ?= "le"

MACHINEOVERRIDES =. "${@bb.utils.contains("MACHINE_ENDIANNESS", "be", "armada38x-be", "armada38x-le", d)}:"

DEFAULTTUNE_armada38x-le ?= "armv7ahf-neon"
DEFAULTTUNE_armada38x-be ?= "armv7ahfb-neon"

MACHINEOVERRIDES =. "armada38x:"

PREFERRED_PROVIDER_u-boot ??= "u-boot-marvell"
PREFERRED_PROVIDER_virtual/bootloader ??= "u-boot-marvell"

PREFERRED_PROVIDER_u-boot-script ??= "u-boot-script-marvell"

# Packages essential for booting
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += " \
    kernel-image \
    kernel-devicetree \
    u-boot-script \
"

IMAGE_FSTYPES += "tar.bz2 ext2 sdcard"
IMAGE_CLASSES += "image_types_marvell"

SERIAL_CONSOLE = "115200 ttyS0"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-marvell"
KERNEL_IMAGETYPE = "zImage"

UBOOT_SUFFIX = "bin"
UBOOT_MACHINE = "armada_38x_config"

MACHINE_FEATURES = "ext2 ipsec nfs pci smbfs usbgadget usbhost vfat"
