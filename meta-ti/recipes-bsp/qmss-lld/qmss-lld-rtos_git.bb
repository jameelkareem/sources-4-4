inherit ti-pdk

require qmss-lld.inc

PR = "${INC_PR}.0"

DEPENDS_append = " rm-lld-rtos"

XDCARGS_k2hk-evm = "k2h k2k"
XDCARGS_k2l-evm = "k2l"
XDCARGS_k2e-evm = "k2e"
XDCARGS_k2g-evm = "k2g"
