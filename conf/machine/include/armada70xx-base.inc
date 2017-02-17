# Provides the Marvell Armada 70xx common settings

require conf/machine/include/arm/arch-arm64.inc

MACHINE_ENDIANNESS ?= "le"

DEFAULTTUNE_armada70xx ?= "${@bb.utils.contains("MACHINE_ENDIANNESS", "le", "aarch64", "aarch64_be", d)}"

MACHINEOVERRIDES =. "armada70xx:"

PREFERRED_PROVIDER_u-boot-script ??= "u-boot-script-marvell"

IMAGE_FSTYPES += "tar.bz2 wic.gz"

WKS_FILES_append = " armada70xx.wks"

# Install wic dependencies
IMAGE_DEPENDS_wic_append = " \
    dosfstools-native \
    mtools-native \
    ${@bb.utils.contains("MACHINE_FEATURES", "edk-efi", "edk-marvell", "", d)} \
"

IMAGE_BOOT_FILES = "\
    Image;${@bb.utils.contains("MACHINE_FEATURES", "edk-efi", "Linux.efi", "Image", d)}\
    ${@bb.utils.contains("MACHINE_FEATURES", "edk-efi", "startup.nsh", "", d)} \
"

SERIAL_CONSOLE = "115200 ttyS0"

PREFERRED_PROVIDER_virtual/kernel ??= "linux-marvell"
KERNEL_IMAGETYPE = "Image"

MACHINE_FEATURES = "ext2 ipsec nfs pci smbfs usbgadget usbhost vfat"

MACHINE_FEATURES_BACKFILL_CONSIDERED += "${@bb.utils.contains("MACHINE_ENDIANNESS", "be", "qemu-usermode", "", d)}"
