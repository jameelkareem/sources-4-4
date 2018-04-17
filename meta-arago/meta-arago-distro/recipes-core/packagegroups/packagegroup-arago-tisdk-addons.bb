DESCRIPTION = "Task to install additional utilities/demos for SDKs"
LICENSE = "MIT"
PR = "r50"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES =+ "${PN}-extra"

#    dt

UTILS = " \
    am-sysinfo \
    gdbserver \
    oprofile \
    nbench-byte \
    trace-cmd \
    arm-benchmarks \
    dropbear \
    openssh-sftp-server \
    ptpd \
    libdrm-kms \
    ${@bb.utils.contains('TUNE_FEATURES', 'armv7a', 'valgrind', '', d)} \
    stream \
"

UTILS_UBOOT_FW = "u-boot-fw-utils"
UTILS_UBOOT_FW_keystone = ""

UTILS_append_ti33x = " mmc-utils \
                       canutils \
                       switch-config \
                       pru-icss \
                       strongswan \
"

UTILS_append_ti43x = " mmc-utils \
                       canutils \
                       switch-config \
                       libdrm-omap \
                       pru-icss \
                       strongswan \
"

UTILS_append_omap-a15 = " mmc-utils \
                          parted \
                          switch-config \
                          libdrm-omap \
                          stream-openmp \
                          pru-icss \
                          strongswan \
                          ti-ipc-rtos-fw \
"

UTILS_append_dra7xx = " canutils \
                        dsptop \
                        gdbc6x \
                        glsdk-example-apps \
"

UTILS_append_keystone = " \
    ti-ipc-rtos-fw \
"

UTILS_append_k2hk-evm = " \
    gdbc6x \
    dsptop \
"

UTILS_append_k2l-evm = " \
    gdbc6x \
    dsptop \
"

UTILS_append_k2e-evm = " \
    gdbc6x \
    dsptop \
"

UTILS_append_k2g-evm = " canutils"

EXTRA_LIBS = ""
EXTRA_LIBS_append_omap-a15 = " cmem"

DEVTOOLS = " \
    packagegroup-core-buildessential \
    packagegroup-core-tools-debug \
    git \
"

EXTRA_PACKAGES = " \
    nodejs \
    nodejs-npm \
    protobuf \
"

RDEPENDS_${PN} = "\
    ${UTILS} \
    ${UTILS_UBOOT_FW} \
    ${DEVTOOLS} \
    ${EXTRA_LIBS} \
"

RDEPENDS_${PN}-extra = "\
    ${EXTRA_PACKAGES} \
"
