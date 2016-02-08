JAXRS-Example
**************

GET DUMMY:
***********
http://localhost:8080/JAXRS-Example/rest/person/99/getDummy
Set the following - 
Accept: application/xml
content-type: application/xml


Add:
****
http://localhost:8080/JAXRS-Example/person/add
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<person>
   <age>30</age>
   <id>1</id>
   <name>Pankaj</name>
</person>


GET:
****
http://localhost:8080/JAXRS-Example/person/1/get

GetAll:
******
http://localhost:8080/JAXRS-Example/person/1/getAll

Delete:
*******
http://localhost:8080/JAXRS-Example/person/1/delete


Swagger URL:
************
http://localhost:8080/JAXRS-Example/docs/
