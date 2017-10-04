## spring-boot-jwt
This is a small test application that lets users create and manage Tasks. 

### Technical Capabilities
* The only public endpoint in this application is _/users/sign-up_. Using this endpoint, users can POST a request which will create a username / password pair in the in-memory HSQLDB database.
<code>curl -H "Content-Type: application/json" -X POST -d '{ "username": "admin","password": "password"}' http://localhost:8080/users/sign-up</code>

* Using the newly created username/password pair, the user could log into the app via POST request at: _/login_.This login request will return a JWT. I am nto sure why, but I can't get a response back doing this POST request in POSTMAN (even though the status code is 200). However, commandline _curl_ works just fine.
<code>curl -i -H "Content-Type: application/json" -X POST -d '{"username": "admin","password": "password"}' http://localhost:8080/login</code>

* Using the JWT as a Bearer token, users can then be authorized into this app (where _xxx.yyy.zzz_ is the place holder for the JWT)
<code>curl -H "Content-Type: application/json" -H "Authorization: Bearer xxx.yyy.zzz" -X POST -d '{"description": "Buy watermelon"}' http://localhost:8080/tasks</code>

* Once authorized, users can proceed to create / manage tasks, like so: 
    * List all tasks: <code>curl -X GET http://localhost:8080/tasks</code>
    * Get a specific task by ID: <code>curl -X GET http://localhost:8080/tasks/1</code>
    * Update a specific task by ID: <code>curl -H "Content-Type: application/json" -X PUT -d '{"description": "Tighten security for JWT Application!"}' http://localhost:8080/tasks/1</code>
    * Delete a specific task by ID: <code>curl -X DELETE http://localhost:8080/tasks/1</code>
    * Create a new task: <code>curl -H "Content-Type: application/json" -X POST -d '{"description": "Test JWT application"}' http://localhost:8080/tasks</code>
    
### API Documentation
API documentation is powered by Swagger! To view API documentation, go to:
* [For HTML documentation](http://localhost:8080/swagger-ui.html)
* [For JSON documentation](http://localhost:8080/v2/api-docs)