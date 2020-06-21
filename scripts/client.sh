#!/usr/bin/env bash
set -euo pipefail
USE_HTTP=${USE_HTTP:-false}
API=http://localhost:8080

test-top-level-curl() {
  curl $API
}

test-top-level-http() {
  http $API
}

test-br-cities-curl() {
  curl $API/br-cities
}

test-br-cities-http() {
  http $API/br-cities
}

test-br-cities-create-curl() {
  local name=$1
  local state=$2
  curl -i -H "Content-Type:application/json" \
    -d "{\"name\": \"$name\", \"state\": \"$state\"}" \
    $API/br-cities
}

test-br-cities-retrieve-curl() {
  local id=$1
  curl $API/br-cities/$id
}

test-br-cities-update-curl() {
  local id=$1
  local name=$2
  local state=$3
  curl -X PUT -H "Content-Type:application/json" \
    -d "{\"name\": \"$name\", \"state\": \"$state\"}" \
    $API/br-cities/$id
}

test-br-cities-patch-curl() {
  local id=$1
  local name=$2
  local state=$3
  curl -X PATCH -H "Content-Type:application/json" \
    -d "{\"name\": \"$name\", \"state\": \"$state\"}" \
    $API/br-cities/$id
}

test-br-cities-delete-curl() {
  local id=$1
  curl -X DELETE $API/br-cities/$id
}

test-br-cities-search-curl() {
  curl $API/br-cities/search
}

test-br-cities-search-findByName-curl() {
  local name="$1"
  curl $API/br-cities/search/findByName?name="$name"
}

test-br-cities-search-findByState-curl() {
  local state="$1"
  curl $API/br-cities/search/findByState?state="$state"
}

usage() {
  cat <<-EOF
	Usage:
	$ $0 <action> [parameters]
	Possible actions:
$(sed -n '/^test-.*-curl/p' client.sh | sed 's/test-\(.*\)-curl.*/\1/g' | cat -n -)
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
  test-$action-$tool "${@:-}"
}

cd "`dirname "$0"`"
action="${1:-}"
shift || :
[ "$action" ] || usage
http_installed=true
$USE_HTTP && {
  which http &> /dev/null || {
    echo "Warning: http not found, install it first! (https://httpie.org)"
    echo "We'll fall back to curl ..."
    http_installed=false
  }
} || which curl &> /dev/null || { echo "Install curl!"; exit 1; }
if $USE_HTTP && $http_installed; then
  run-test http $action "${@:-}"
else
  run-test curl $action "${@:-}"
fi
