# Coursera project for Haemimont 

I've created Spring Boot project with Maven for this task. 
absolutely love to divide my work into smaller tasks, that help me to structure my steps and to speed up my coding.

1. First step that I implemented was to configure my database and my project properties. I used yaml file in which  to connect to  DB (MySQL) you have to create environment variables to set the properties:
   MYSQL_USER,
   MYSQL_PASSWORD, 
   DB_URL

2. Second step for me was to create my entity tables. I've used jakarta.persistence and jakarta.validation to anotate my entity classes and their fiels. With this annotations the models will automaicaly map themselves to db  tables.

  This is my db diagram:  
  ![diagram](https://github.com/Kris13081/Coursera_Haemimont/assets/87601009/a43185b0-affb-4840-b24a-95de88a41154)


3. The third step was to create a class called "CompletionTimeInclude" to create the third field in my "student_courses-xref" mapping table:
   - In this class I used CommandLineRunner to execute method that retrives my Db connection parammeters from the  properties.yaml file using @Value annotation. Then I wrote method that checks if this field already exists and if it doesnt
     it runs a INSERT query that creates the "completion_date" field. (This class is located in: bg.haemimont.coursera.util package)



THE PROJECT IS STILL IN DEVELOPMENT! 
I will upload more from it this evening (2/2/2024)
