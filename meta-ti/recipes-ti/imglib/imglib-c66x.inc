DESCRIPTION = "IMGLIB contains optimized general-purpose image/video processing routines for TI DSPs"
HOMEPAGE = "http://www.ti.com/tool/sprc264"
SECTION = "devel"
LICENSE = "BSD-3-Clause"

require ../includes/ti-paths.inc
require ../includes/ti-staging.inc
require ../includes/ti-unpack.inc

COMPATIBLE_MACHINE = "omap-a15|keystone"

SRC_URI = "http://software-dl.ti.com/sdoemb/sdoemb_public_sw/imglib/${PV}/exports/${BINFILE};name=imglib"

S = "${WORKDIR}/imglib-c66x-${PV}"

BINFILE = "imglib_c66x_${PV}_Linux.bin"
TI_BIN_UNPK_ARGS = "--mode silent --prefix ${S}"
TI_BIN_UNPK_CMDS = ""

do_install() {
    install -d ${D}${IMGLIB_C66_INSTALL_DIR_RECIPE}
    cp -pPrf ${S}/* ${D}${IMGLIB_C66_INSTALL_DIR_RECIPE}
}

ALLOW_EMPTY_${PN} = "1"
FILES_${PN}-dev += "${IMGLIB_C66_INSTALL_DIR_RECIPE}"

RDEPENDS_${PN}-dev += "perl"
INSANE_SKIP_${PN}-dev = "arch"
