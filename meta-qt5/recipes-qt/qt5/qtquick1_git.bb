require qt5.inc
require qt5-git.inc

# There are no LGPLv3-only licensed files in this component.
# There are no GPLv2 licensed files in this component.
# Note that some files are LGPL-2.1 only without The-Qt-Company-Qt-LGPL-Exception-1.1.
LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=b8c75190712063cde04e1f41b6fdad98 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=05832301944453ec79e40ba3c3cfceec \
"

DEPENDS += "qtscript qtsvg qtxmlpatterns"
# qttools

PACKAGECONFIG ??= "webkit"
PACKAGECONFIG[webkit] = "CONFIG+=enable-webkit,CONFIG-=enable-webkit,qtwebkit"

do_configure_prepend() {
    sed -i 's#^qtHaveModule(webkitwidgets):#enable-webkit:qtHaveModule(webkitwidgets):#g' ${S}/src/imports/imports.pro
}

SRCREV = "563ce3888f3c04abcc96ba236b20c7a822bc88e2"
