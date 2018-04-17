PR_append = ".tisdk0"

# This corresponds to version 02.01.00.00
SRCREV = "19761495883bf8fb2670b0929d255135bd295dcb"

PV = "2.1.0.0+git${SRCPV}"

COMPATIBLE_MACHINE = "keystone|am57xx-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEVICE_NAME_am57xx-evm = "am57xx"
DEVICE_NAME_keystone = "keystone"

do_compile() {
	oe_runmake -C ${S} test "DEVICE=${DEVICE_NAME}"
}

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/test/uio_mem_test ${D}${bindir}/.
	install -c -m 755 ${S}/test/uio_int_test ${D}${bindir}/.
}

do_install_append_keystone () {
	install -c -m 755 ${S}/test/uio_cic2_int_multithread_test ${D}${bindir}/.
}

do_install_append_am57xx-evm () {
	install -c -m 755 ${S}/test/prussdrv_test/test/pruss_uio_test ${D}${bindir}/.
}
