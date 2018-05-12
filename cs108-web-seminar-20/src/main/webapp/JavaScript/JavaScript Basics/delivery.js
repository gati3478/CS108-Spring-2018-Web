var zipcodeArray = new Array("94025", "94301", "94304", "94305", "94306", "94022");

function checkAvailability(zipcode) {
  for (var i = 0; i < zipcodeArray.length; i++) {
    if (zipcodeArray[i] == zipcode) {
      return true;
    }
  }

  return false;
}
