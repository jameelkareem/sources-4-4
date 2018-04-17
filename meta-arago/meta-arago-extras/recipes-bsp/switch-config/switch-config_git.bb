DESCRIPTION = "Ethernet Switch configuration management"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://switch-config.c;beginline=1;endline=14;md5=659ff9658cbaba3110b81804af60de75"

DEPENDS = "virtual/kernel"

PV = "2.0"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH ?= "v4.1"
SRCREV = "669334329dff132c4a093351d48252b12e995b6c"

SRC_URI = "git://git.ti.com/switch-config/switch-config.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

do_configure() {
	mkdir -p ${S}/linux
	cp ${STAGING_KERNEL_DIR}/include/uapi/linux/net_switch_config.h ${S}/linux
	cp ${STAGING_KERNEL_DIR}/include/uapi/linux/sockios.h ${S}/linux
	sed 's|-I$(KBUILD_OUTPUT)/usr/include|${TOOLCHAIN_OPTIONS} -I.|' -i ${S}/Makefile
}

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/switch-config ${D}${bindir}/switch-config
}
