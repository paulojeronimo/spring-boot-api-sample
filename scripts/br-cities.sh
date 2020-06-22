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
