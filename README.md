## spring-boot-jwt
This is a small test application that lets users create Tasks. 

### Technical Capabilities
* The only public endpoint in this application is _/users/sign-up_. Using this endpoint, users can POST a request which will create a username / password pair in the in-memory HSQLDB database.
<code>curl -H "Content-Type: application/json" -X POST -d '{ "username": "admin","password": "password"}' http://localhost:8080/users/sign-up</code>

* Using the newly created username/password pair, the user could log into the app via POST reuqest at: _/login_ The login request will return a JWT. (Not sure why, but couldn't get a response back doing this POST request in POSTMAN, but commandline curl works just fine)
<code>curl -i -H "Content-Type: application/json" -X POST -d '{"username": "admin","password": "password"}' http://localhost:8080/login</code>

* Using the JWT as a Bearer token, users can then be authorized into this app (where _xxx.yyy.zzz_ is the place holder for the JWT)
<code>curl -H "Content-Type: application/json" -H "Authorization: Bearer xxx.yyy.zzz" -X POST -d '{"description": "Buy watermelon"}'  http://localhost:8080/tasks</code>
