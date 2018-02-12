require recipes-kernel/kernel-modules/kernel-module-marvell-musdk.inc

RPROVIDES_${PN} += "kernel-module-marvell-musdk-uio"

KERNEL_MODULES_META_PACKAGE = "${PN}"

do_compile[dirs] = "${S}/modules/uio"

do_install() {
    install -Dm 0644 ${S}/modules/uio/musdk_uio.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/musdk/musdk_uio.ko
}
