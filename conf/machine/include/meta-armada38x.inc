MACHINEOVERRIDES =. "armada38x:"

# Kernel modules are recommended, but not required for the machine to boot
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules"

# boot.scr, DTB, and uImage are required for booting
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "kernel-image kernel-devicetree u-boot-script-marvell"

# Build U-Boot as part of a final image, even though it does not affect the RFS
EXTRA_IMAGEDEPENDS += "u-boot"

DEFAULTTUNE ?= "cortexa9hf-neon"
require conf/machine/include/tune-cortexa9.inc

IMAGE_FSTYPES += "tar.bz2 ext2 sdcard"
IMAGE_CLASSES += "image_types_marvell"

SERIAL_CONSOLE = "115200 ttyS0"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto-custom"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot = "u-boot-marvell"
UBOOT_SUFFIX = "bin"
UBOOT_MACHINE = "armada_38x_config"

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

MACHINE_FEATURES = "ext2 ipsec nfs pci smbfs usbgadget usbhost vfat"
