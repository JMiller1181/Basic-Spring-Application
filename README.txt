The models are self-explanatory, they were create to the specifications in the instructions

The repositories were also created as directed in the instructions. They allow the user to save and edit the data
on the h2 database for the respective models

the h2 database is just a temp database created to allow for the saving and changing of data. Each time the program
is run a new instance is created, and the instance is deleted once the program ends. If a configuration was added in the
application.properties file then it could persist.

The controllers do essentially the same things.

1) The list function looks at the database and will populate a table with
each entity it finds. The thymeleaf template takes the entity/object and collects the fields from the object to populate
the page with the relevant data. The template also takes the id and links to other templates that will execute the
corresponding actions on the appropriate ID

2) The create function allows the user to create a new entity on the database. The thymeleaf template will take the data
from the relevant fields and put that data into the new object created in the corresponding fields, then it gets saved
by the repository

3) The update function finds an entity by its id and populates the corresponding fields as defined by the thymeleaf
template with the @GetMapping. Then the user can change those fields and the thymeleaf template will save the new data
in the correct fields of the object, which is then saved to the repository in its updated state with the @PostMapping

4)The delete function finds an entity by its id and takes the user to a delete page where it displays the id to be
deleted. The ID is autofilled with the id of the entity that was clicked for deletion, but the user can select a new ID.
Once the user hits delete the entity with the corresponding id is simply deleted from the database.