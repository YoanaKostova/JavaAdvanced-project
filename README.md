# Java Advanced - Company 
This project is written for Java Advanced course. The project stores information about the company. It
provides information about all employees of the company, all projects and information
about the different departments of the company.For each employee there is information in which department he works.
For each department there is information on which projects it works on.

The Spring Boot framework was used to create the project.
The project used H2 for Database.
CRUD requests have been implemented for each of the individual components.

Main functionality:
1. Find an employee by name
	/employee/ (@RequestParam firstname, @RequestParam lastname)
2. Find a project by name
	/project/ (@RequestParam name)
3. Find all employees in the department
	/employee/ (@RequestParam departmentId)
	/department/allEmployees (@RequestParam departmentId)
4. Find salary sum of the department
	/department/salary/{id}