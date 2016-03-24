# Marvell Kernel config fragments extension
#
# This allow to configuration fragments to be used inside the SRC_URI
# variable. The fragments must end with the .cfg file extension to be
# processed by this class.
#
# This class is based on cml1-config.bbclass from Mentor Graphics.
#
# Copyright 2016 (C) O.S. Systems Software LTDA.

######
### Utilities
#####

merge_fragments () {
    list_fragments | ${merge_fragment_pipeline} >"${B}/fragments"
    merge_config.sh -m "$1" $(cat "${B}/fragments")
}

list_fragments () {
    cat <<END
    ${@"\n".join(src_config_fragments(d))}
END
}

def src_config_fragments(d):
    sources = src_patches(d, True)
    return [s for s in sources if s.endswith('.cfg')]

######

merge_fragment_pipeline = "cat"

do_configure_append() {
    merge_fragments ${B}/.config
}
