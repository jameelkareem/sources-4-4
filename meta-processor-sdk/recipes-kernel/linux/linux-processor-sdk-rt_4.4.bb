require linux-processor-sdk_4.4.bb

# Look in the generic major.minor directory for files
# This will have priority over generic non-rt path
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.4:"

BRANCH = "processor-sdk-linux-rt-03.00.00"

SRCREV = "86c9060003eb5fc0b14aa40e62d662f7a6143856"
