# Marvell Kernel LOCALVERSION extension
#
# This allow to easy reuse of code between different kernel recipes
#
# The following options are supported:
#
#  SCMVERSION        Puts the Git hash in kernel local version
#  LOCALVERSION      Value used in LOCALVERSION (default to '+marvell')
#
# Copyright 2014-2016 (C) O.S. Systems Software LTDA.

SCMVERSION ??= "y"
LOCALVERSION ??= "+marvell"

kernel_conf_variable() {
	CONF_SED_SCRIPT="$CONF_SED_SCRIPT /CONFIG_$1[ =]/d;"
	if test "$2" = "n"
	then
		echo "# CONFIG_$1 is not set" >> ${B}/.config
	else
		echo "CONFIG_$1=$2" >> ${B}/.config
	fi
}

do_configure_append() {
	CONF_SED_SCRIPT=""

	sed -e "${CONF_SED_SCRIPT}" -i '${B}/.config'

	kernel_conf_variable LOCALVERSION "\"${LOCALVERSION}\""
	kernel_conf_variable LOCALVERSION_AUTO y

	if [ "${SCMVERSION}" = "y" ]; then
		# Add GIT revision to the local version
		head=`git --git-dir=${S}/.git rev-parse --verify --short HEAD 2> /dev/null`
		printf "%s%s" +g $head > ${S}/.scmversion
	fi
}
