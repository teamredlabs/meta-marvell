DESCRIPTION = "OpenFastPath is an open source implementation of a high \
performance TCP/IP stack providing features that network application \
developers need to cope with today’s fast-paced network."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fbe4957c430eed6cc20521d4eb429fae"

DEPENDS = "odp-marvell openssl"

SRCBRANCH = "ofp-armada-17.10"
SRCREV = "08ffe8099c51081cb84251175b88e309f1562fd7"
SRC_URI = " \
    git://git@github.com/MarvellEmbeddedProcessors/ofp-marvell.git;branch=${SRCBRANCH};protocol=https \
    file://0001-scripts-git_hash.sh-Add-always-argument-to-git-descr.patch \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_install_append() {
    install -Dm 0755 ${B}/example/fpm/fpm ${D}${bindir}/fpm
    install -Dm 0755 ${B}/example/fpm_burstmode/fpm_burstmode ${D}${bindir}/fpm_burstmode
}

PACKAGES =+ "${PN}-examples"
FILES_${PN}-examples = " \
    ${bindir}/fpm \
    ${bindir}/fpm_burstmode \
"

COMPATIBLE_MACHINE = "(armada70xx|armada80xx)"
