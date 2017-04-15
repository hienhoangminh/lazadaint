# RestAssured Java
Code for API testing with Java

Here are the details to run the project:

1 . Setup the server:

* Requirements:
 - NodeJS is installed.
 - Json-server is installed.
 - Eclipse is installed.
 
* Optional:
- The server could be different. Change the server information in server.properties.
- You could be asked to change the path of data files. Change the path of datas file inside data.properties.
 
* Setup:
- Open the command line.
- Run this following command line to launch the fake API : json-server --watch "location/of/file/json".
  -- In this case, name of json file is db.json, which is located inside server folder.
- Launch the following URL : http://localhost:3000/db in browser to verify that everything goes well.

2. Run the test:
- Click on the the testng.xml file and Run as > TestNg Suite.
