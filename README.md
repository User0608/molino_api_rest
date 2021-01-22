# Proyecto de api rest.

## Rutas

- `/usuario/authorization` : Seguridad

	Para iniciar session en le sistema se deberá de pasar como datos el usuario y password, en formato json
	al path: `/usuario/authorization`, usando como verbo POST.
	``` json
		{
			"username":"kevin002",
			"password":"maira002"
		}
	```
	Del cual se obtendrá como respuesta un token, el que sera empleado para autentificarse en todas las demás operaciones asignadas al usuario en base a la lista de roles que esta asignado.
	``` json
		{
		"token": "eyJ0eXiJIUzI1NiJ9.eyJvd25lciI6ImFkbWluIiOiJrZXZpbjAwMiJ9.SliyCzt4usnH4fdaIkuc",
		"username": "kevin002",
		"roles": [
			"ADMIN"
		]
	}
	```