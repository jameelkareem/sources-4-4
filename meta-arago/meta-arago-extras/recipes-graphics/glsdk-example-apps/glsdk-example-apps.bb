SUMMARY = "GLSDK example applications"
HOMEPAGE = "http://git.ti.com/glsdk"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=10a9abb9c5bb19edd83a8cf66eef7148"

DEPENDS = "gstreamer1.0 libdrm ti-ipc"

COMPATIBLE_MACHINE = "dra7xx"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

SRC_URI = "git://git.ti.com/glsdk/example-applications.git;protocol=git \
	 "

SRCREV = "4dcbca6bf486b0331781014c463225e927e1f464"

PR = "r40"

do_install_append () {
    install -d ${D}${sysconfdir}/glsdkstatcoll
    install -m 0644 ${S}/bandwidth-tool/config.ini ${D}${sysconfdir}/glsdkstatcoll/.
    install -m 0644 ${S}/bandwidth-tool/initiators.cfg ${D}${sysconfdir}/glsdkstatcoll/.

    install -d ${D}${sysconfdir}/visualization_scripts
    install -m 0644 ${S}/cpuload-plugins/scripts/*.sh  ${D}${sysconfdir}/visualization_scripts/.
    install -m 0644 ${S}/cpuload-plugins/scripts/*.cfg ${D}${sysconfdir}/visualization_scripts/.
}

FILES_${PN} += "${sysconfdir}/glsdkstatcoll/* ${sysconfdir}/visualization_scripts/*"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
