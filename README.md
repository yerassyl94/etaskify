# Etaskify

## Services

* organizations
* authorizations
* taskManager
* userManager

## Run
```sh
./run.sh
```

## Endpoints

***baseUrl:*** ***localhost:7777/api***

### Create admin and organisation

* ***POST*** `/admin/organization`
 ```json
 {
 "name": String,
 "phone": String,
 "address": String,
 "adminUsername": String,
 "adminEmail": String,
 "adminPassword": String
 }
 ```
 * ***POST*** `/admin/login`
 ```json
{
	"username": String,
	"password": String
}
```

### Create User
* ***POST*** `/manager/createUser`
```json
{
	"name": String,
	"surname": String,
	"email": String
}
```
*Admin Bearer Token is required*

### Authenticate User
* ***POST*** `/user/login`
```json
{
	"email": String,
	"password": String
}
```
*default password: Abc123456*

### Task
* ***POST*** `/task`
```json
{
	"title": String,
	"description": String,
	"status": String,
	"deadline": LocalDate,
	"assignees": Set<UUID>,
	"organizationId": UUID
}
```
*User bearer token is required*
* ***GET*** `/task/<organization_id>/tasks`