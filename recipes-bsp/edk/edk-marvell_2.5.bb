require recipes-bsp/edk/edk-marvell.inc

SRCREV = "07d47c9c71aff89c9d19c8fc1b74e6e1c2986076"
SRCBRANCH = "uefi-armada-17.10"
SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/uefi-marvell.git;branch=${SRCBRANCH};protocol=https \
    file://Change-header-path-and-link-name-to-use-OSSP-uuid.patch \
    file://Remove-bashism.patch \
    file://Remove-Werror-from-CFLAGS-to-avoid-build-errors-with.patch \
    file://vrf-update-MAX_PATH.patch \
    file://BaseTools-Source-C-Makefiles-header.makefile-Add-nat.patch \
    file://startup.nsh \
"
