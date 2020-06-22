#!/usr/bin/env bash
set -euo pipefail
USE_HTTP=${USE_HTTP:-false}
API=http://localhost:8080
SCRIPTS="top-level br-cities customers"

cd "$(dirname "$0")"
for f in $SCRIPTS; do source "$PWD/$f.sh"; done

check-tools() {
  local dependency_not_found=false
  which sed &> /dev/null || {
    echo "Please, install sed!"
    dependency_not_found=true
  }
  which curl &> /dev/null || {
    echo "Please, install curl!"
    dependency_not_found=true
  }
  which http &> /dev/null || {
    echo "WARNING: HTTPie (https://httpie.org/) not installed, we'll use curl!"
  }
  which jq &> /dev/null || {
    echo "Please, install jq! (https://stedolan.github.io/jq/)"
    dependency_not_found=true
  }
  ! $dependency_not_found || exit 1
}

usage() {
  cat <<-EOF
	Usage: $0 <action> [-h | parameters]
	Possible actions:
$(for f in $SCRIPTS; do
sed -n '/^test-.*-curl/p' $f.sh | sed 's/test-\(.*\)-curl.*/\1/g'
done | cat -n -)
	EOF
  exit 0
}

run-test() {
  local tool=$1; shift
  local action=$1; shift || :
  type test-$action-$tool &> /dev/null || {
    echo "test-$action-$tool function not found!"
    exit 1
  }
  if [ "${1:-}" = "-h" ]
  then
    type test-$action-$tool
  else
    test-$action-$tool "${@:-}"
  fi
}

check-tools
action="${1:-}"
shift || :
[ "$action" ] || usage
http_installed=true
$USE_HTTP && {
  which http &> /dev/null || http_installed=false
}
if $USE_HTTP && $http_installed; then
  run-test http $action "${@:-}"
else
  run-test curl $action "${@:-}"
fi
