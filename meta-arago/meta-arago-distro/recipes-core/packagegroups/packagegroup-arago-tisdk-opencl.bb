DESCRIPTION = "Task to install OpenCL/MP and related components"
LICENSE = "MIT"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES =+ "${PN}-extra"

MAIN_PKGS = " \
    opencl-examples \
"

EXTRA_PKGS = " \
    opencl-staticdev \
    opencl-examples-dev \
    openmpacc-examples-dev \
    python-pyopencl \
"

EXTRA_PKGS_append_k2hk-evm = " \
    linalg-examples \
"

RDEPENDS_${PN} = "\
    ${MAIN_PKGS} \
"

RDEPENDS_${PN}-extra = "\
    ${EXTRA_PKGS} \
"
