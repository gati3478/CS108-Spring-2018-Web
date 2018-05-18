var locationArray =[{zipcode: "94025", location: "1234 ABC Street"},
                    {zipcode: "94301", location: "333 D Street"},
                    {zipcode: "94304", location: "1 E Street"},
                    {zipcode: "94305", location: "9 F Ave"}];

function findLocation(zipcode) {
  for (var i = 0; i < locationArray.length; i++) {
    if (locationArray[i].zipcode === zipcode) {
      return locationArray[i].location;
    }
  }
  return "Not Found";
}
