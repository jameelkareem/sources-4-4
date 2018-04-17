DESCRIPTION = "TI OpenCL compute device firmware"
HOMEPAGE = "http://software-dl.ti.com/mctools/docs/opencl/intro.html"
LICENSE = "BSD"

include ocl.inc
require recipes-ti/includes/ti-paths.inc

PR = "${INC_PR}.0"

inherit cmake update-alternatives

DEPENDS = " ti-llvm3.6-native \
            common-csl-ip-rtos \
            ti-xdctools \
            ti-ipc-rtos \
            ti-sysbios \
            ti-cgt6x-native \
            edma3-lld-rtos \
            ti-xdais \
            ti-framework-components \
            libulm \
            gdbserver-c6x \
            openmp-rtos \
"

DEPENDS_append_k2hk-evm = " multiprocmgr-rtos \
                            qmss-lld-rtos \
                            cppi-lld-rtos \
                            rm-lld-rtos \
"

DEPENDS_append_k2l-evm  = " multiprocmgr-rtos \
                            qmss-lld-rtos \
                            cppi-lld-rtos \
                            rm-lld-rtos \
"

DEPENDS_append_k2e-evm  = " multiprocmgr-rtos \
                            qmss-lld-rtos \
                            cppi-lld-rtos \
                            rm-lld-rtos \
"

COMPATIBLE_MACHINE = "dra7xx|keystone"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git/monitor"
S_dra7xx = "${WORKDIR}/git/monitor_vayu"
S_k2g-evm = "${WORKDIR}/git/monitor_vayu"

BUILD_TARGET_k2hk-evm = "ARM_K2H"
BUILD_TARGET_k2l-evm  = "ARM_K2L"
BUILD_TARGET_k2e-evm  = "ARM_K2E"
BUILD_TARGET_dra7xx   = "ARM_AM57"
BUILD_TARGET_k2g-evm  = "ARM_K2G"

EXTRA_OECMAKE += " -DCROSS_COMPILE=TRUE \
                   -DOCL_MONITOR_DIR=${S} \
                   -DBUILD_OUTPUT=all \
                   -DSHARE_PATH=${datadir}/ti \
                   -DBUILD_TARGET=${BUILD_TARGET} \
"

export TI_OCL_CGT_INSTALL = "${STAGING_DIR_NATIVE}/usr/share/ti/cgt-c6x"
export AM57_PDK_DIR = "${PDK_INSTALL_DIR}"
export C6636_PDK_DIR = "${PDK_INSTALL_DIR}"
export IPC_DIR = "${IPC_INSTALL_DIR}"
export XDC_DIR = "${XDC_INSTALL_DIR}"
export BIOS_DIR = "${SYSBIOS_INSTALL_DIR}"
export EDMA3LLD_DIR = "${EDMA3_LLD_INSTALL_DIR}"
export XDAIS_DIR = "${XDAIS_INSTALL_DIR}"
export FC_DIR = "${FC_INSTALL_DIR}"
export MPM_DIR = "${MPM_INSTALL_DIR}"
export OMP_DIR = "${OMP_INSTALL_DIR}"
export ULM_DIR ="${STAGING_DIR_TARGET}/usr/share/ti/ulm"
export GDB_SERVER_DIR = "${STAGING_DIR_TARGET}/usr/share/ti/gdbc6x"
export X86_LLVM_DIR = "${STAGING_DIR_NATIVE}/usr"
export XDCPATH = "${S};${IPC_DIR}/packages;${BIOS_DIR}/packages;${EDMA3LLD_DIR}/packages;${FC_DIR}/packages;${XDAIS_DIR}/packages"

do_install_append_dra7xx() {
	for i in 1 2; do mv ${D}${base_libdir}/firmware/dra7-dsp$i-fw.xe66 ${D}${base_libdir}/firmware/dra7-dsp$i-fw.xe66.${BPN}; done
}

ALTERNATIVE_${PN} = "dra7-dsp1-fw.xe66 dra7-dsp2-fw.xe66"
ALTERNATIVE_LINK_NAME[dra7-dsp1-fw.xe66] = "${base_libdir}/firmware/dra7-dsp1-fw.xe66"
ALTERNATIVE_TARGET[dra7-dsp1-fw.xe66] = "${base_libdir}/firmware/dra7-dsp1-fw.xe66.${BPN}"
ALTERNATIVE_LINK_NAME[dra7-dsp2-fw.xe66] = "${base_libdir}/firmware/dra7-dsp2-fw.xe66"
ALTERNATIVE_TARGET[dra7-dsp2-fw.xe66] = "${base_libdir}/firmware/dra7-dsp2-fw.xe66.${BPN}"
ALTERNATIVE_PRIORITY = "100"

MONITOR_FIRMWARE = ""
MONITOR_FIRMWARE_dra7xx = "${base_libdir}/firmware/*"

FILES_${PN} += " \
    ${datadir}/ti/opencl/* \
    ${MONITOR_FIRMWARE} \
"

INSANE_SKIP_${PN} = "arch"
