require recipes-bsp/edk/edk-marvell.inc

SRCREV = "00eb373cb2b6ed36f9c034a3112e50a56f3320ea"
SRCBRANCH = "uefi-armada-17.08"
SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/uefi-marvell.git;branch=${SRCBRANCH};protocol=https \
    file://Change-header-path-and-link-name-to-use-OSSP-uuid.patch \
    file://Remove-bashism.patch \
    file://Remove-Werror-from-CFLAGS-to-avoid-build-errors-with.patch \
    file://vrf-update-MAX_PATH.patch \
    file://BaseTools-Source-C-Makefiles-header.makefile-Add-nat.patch \
    file://startup.nsh \
"
