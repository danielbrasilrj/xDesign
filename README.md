# xDesign
xDesign

Running application:
mvn spring-boot:run

Calling munro service:
GET http://localhost:8080/find-by-filter

Filters:
munroCategory: {"MUN", "TOP"}
sortBy
  property: {"HEIGHT_METER", "NAME"}
  direction: {"ASC", "DESC"}


JSON example to use in the request body:
{
    "limit": "10",
    "minHeight": "914.5",
    "maxHeight": "914.7",
    "munroCategory": "TOP",
    "sortBy":
        {
            "property":"HEIGHT_METER",
            "direction":"DESC"
        }
}
