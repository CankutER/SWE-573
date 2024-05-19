export function FilterPost(post, postFilters) {
  let filterPass = true;
  console.log(post);
  post.postFields.forEach((postField) => {
    console.log(postField);
    console.log(postFilters[postField.dataField.id]);
    switch (postField.dataField.dataFieldType.type) {
      case "string":
        filterPass = filterString(
          postField,
          postFilters[postField.dataField.id]
        );
        break;
      case "number":
        filterPass = filterNumber(
          postField,
          postFilters[postField.dataField.id]
        );
        break;
      case "date":
        filterPass = filterDate(postField, postFilters[postField.dataField.id]);
        break;
      case "image":
        filterPass = true;
        break;
      case "geolocation":
        filterPass = filterLocation(
          postField,
          postFilters[postField.dataField.id]
        );
        break;
      default:
        console.log(
          `Unknown data postField type: ${postField.dataField.dataFieldType.type}`
        );
        filterPass = false;
    }
  });
  return filterPass;
}

function filterNumber(postField, filter) {
  if (Number(filter.max) === 0 && Number(filter.min) === 0) return true;
  return (
    Number(postField.value) >= Number(filter.min) &&
    Number(postField.value) <= Number(filter.max)
  );
}

function filterDate(postField, filter) {
  if (Number(filter.start) === 0 && Number(filter.end) === 0) return true;
  if (Number(filter.start) === 0)
    return Date(postField.value) <= Date(filter.end);
  if (Number(filter.end) === 0)
    return Date(postField.value) >= Date(filter.start);
  return (
    Date(postField.value) <= Date(filter.end) &&
    Date(postField.value) >= Date(filter.start)
  );
}
function filterString(postField, filter) {
  if (filter.value === "") return true;
  return postField.value.includes(filter.value);
}
function filterLocation(postField, filter) {
  console.log(filter);
  if (Number(filter.proximity) === 0) return true;
  const latTarget = parseFloat(postField.value.split(",")[0].trim());
  const longTarget = parseFloat(postField.value.split(",")[1].trim());
  const latAnchor = parseFloat(filter.coords.split(",")[0].trim());
  const longAnchor = parseFloat(filter.coords.split(",")[1].trim());
  const latTargetRad = radians(latTarget);
  const longTargetRad = radians(longTarget);
  const latAnchorRad = radians(latAnchor);
  const longAnchorRad = radians(longAnchor);
  const dLat = latAnchorRad - latTargetRad;
  const dLon = longAnchorRad - longTargetRad;

  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(latTargetRad) *
      Math.cos(latAnchorRad) *
      Math.sin(dLon / 2) *
      Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

  const earthRadiusKm = 6371;

  // Distance in kilometers
  const distanceKm = earthRadiusKm * c;
  return distanceKm <= Number(filter.proximity);
}

function radians(degrees) {
  return (degrees * Math.PI) / 180;
}
