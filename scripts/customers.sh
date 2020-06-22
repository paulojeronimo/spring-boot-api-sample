test-customers-curl() {
  curl $API/customers
}

test-customers-create-curl() {
  local name=$1
  local gender=$2
  local birthday=$3
  local city_id=$4
  curl -i -H "Content-Type:application/json" \
    -d "{\"name\": \"$name\", \"gender\": \"$gender\", \"birthday\": \"$birthday\"}" \
    $API/customers | tee customers.last-created.txt
  echo
  curl -i -X PUT -H "Content-Type:text/uri-list" \
    -d "$API/br-cities/$city_id" \
    `jq -r ._links.city.href <(tail -n +10 customers.last-created.txt)`
}

test-customers-retrieve-curl() {
  local id=$1
  curl $API/customers/$id
}

test-customers-update-curl() {
  local id=$1
  local name=$2
  local gender=$3
  local birthday=$4
  local city_id=$5
  curl -X PUT -H "Content-Type:application/json" \
    -d "{\"name\": \"$name\", \"gender\": \"$gender\", \"birthday\": \"$birthday\"}" \
    $API/customers/$id
  echo
  curl -i -X PUT -H "Content-Type:text/uri-list" \
    -d "$API/br-cities/$city_id" \
    $API/customers/$id/city
}

test-customers-delete-curl() {
  local id=$1
  curl -X DELETE $API/customers/$id
}

test-customers-search-curl() {
  curl $API/customers/search
}

test-customers-search-findByName-curl() {
  local name="$1"
  curl $API/customers/search/findByName?name="$name"
}

test-customers-search-findByCityId-curl() {
  local city_id="$1"
  curl $API/customers/search/findByCityId?city_id="$city_id"
}
