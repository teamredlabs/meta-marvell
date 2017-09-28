require recipes-kernel/linux/linux-marvell_4.4.52.bb

SRCREV = "96fdc8ecd9a5971817df57c6650c2ac97e6f2fdf"

SRC_URI += " \
    file://0001-musdk-defconfig-modifications.patch \
    file://0002-musdk-add-new-dts-dtsi-files.patch \
    file://0003-musdk-update-existing-dts-files.patch \
    file://0004-musdk-mvpp2x-module-params-updates.patch \
    file://0005-musdk-dynamic-flow-table-support.patch \
"

COMPATIBLE_MACHINE = "(armada70xx|armada80xx|armada37xx)"

