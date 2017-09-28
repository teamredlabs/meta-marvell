SUMMARY = "netmap and VALE - very fast packet I/O from userspace (FreeBSD/Linux)"
DESCRIPTION= "NETMAP is a framework for very fast packet I/O from userspace.  VALE is an equally fast in-kernel software switch using the netmap API.  Both are implemented as a single kernel module for FreeBSD and Linux, and can deal with line rate on real or emulated 10 Gbit ports."
SECTION = "networking"
HOMEPAGE = "https://github.com/MarvellEmbeddedProcessors/netmap-marvell"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://README;beginline=15;endline=17;md5=881d9d37140d5df77056d86b4f558724"

SRCBRANCH = "netmap-armada-17.10"
SRCREV = "b0793b08889b862ec0b680b8ec41e4e0eaa3f3cc"
PV = "master+git${SRCPV}"

SRC_URI = "git://git@github.com/MarvellEmbeddedProcessors/netmap-marvell.git;branch=${SRCBRANCH};protocol=https"

S = "${WORKDIR}/git"

DISABLE_STATIC = ""

COMPATIBLE_HOST = '(x86_64|i.86|mips|arm|aarch64).*-linux'
