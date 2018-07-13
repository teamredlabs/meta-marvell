# Provides the Marvell Armada 38x common settings

require conf/machine/include/tune-cortexa9.inc

DEFAULTTUNE_armada38x ??= "armv7ahf-neon"

MACHINEOVERRIDES =. "armada38x:"

PREFERRED_PROVIDER_u-boot ??= "u-boot-marvell"
PREFERRED_PROVIDER_virtual/bootloader ??= "u-boot-marvell"

PREFERRED_PROVIDER_u-boot-fw-utils ?= "u-boot-fw-utils-marvell"
PREFERRED_RPROVIDER_u-boot-fw-utils ?= "u-boot-fw-utils-marvell"

PREFERRED_PROVIDER_u-boot-script ??= "u-boot-script-marvell"

# Packages essential for booting
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += " \
    kernel-image \
    kernel-devicetree \
    u-boot-script \
"

IMAGE_FSTYPES += "tar.bz2 ext2 wic.gz"

WKS_FILES_append = " armada38x.wks"

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

PREFERRED_PROVIDER_virtual/kernel ?= "linux-marvell"
KERNEL_IMAGETYPE = "zImage"

UBOOT_SUFFIX = "bin"
UBOOT_MACHINE = "armada_38x_config"

WKS_FILES_append_armada38x = " armada38x.wks"
IMAGE_DEPENDS_wic_append_armada38x = " virtual/bootloader"

MACHINE_FEATURES = "ext2 ipsec nfs pci smbfs usbgadget usbhost vfat"
