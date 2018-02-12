require recipes-kernel/kernel-modules/kernel-module-marvell-musdk.inc

RPROVIDES_${PN} += "kernel-module-marvell-musdk-pp2"

KERNEL_MODULES_META_PACKAGE = "${PN}"

do_compile[dirs] = "${S}/modules/pp2"

do_install() {
    install -Dm 0644 ${S}/modules/pp2/mv_pp_uio.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/musdk/mv_pp_uio.ko
}
