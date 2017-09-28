require recipes-kernel/kernel-modules/kernel-module-marvell-musdk.inc

KERNEL_MODULES_META_PACKAGE = "${PN}"

do_compile[dirs] = "${S}/modules/sam"

do_install() {
    install -Dm 0644 ${S}/modules/sam/mv_sam_uio.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/musdk/mv_sam_uio.ko
}
