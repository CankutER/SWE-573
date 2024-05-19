import CardText from "react-bootstrap/CardText";

const GeolocationCardField = ({ value, dataField }) => (
  <CardText>
    {dataField.name}: {value}
  </CardText>
);

export default GeolocationCardField;
