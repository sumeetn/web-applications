web-applications
================

Pre-requisite
==============
1. Maven version 3.2+
2. jdk 1.7+


Steps to setup project
======================
1. create a directory, which will be referred as PROJECT_HOME
2. cd <PROJECT_HOME>
3. git clone -b master https://github.com/sumeetn/web-applications.git
4. cd <PROJECT_HOME>/web-applications/puzzles
5. mvn clean install
6. mvn tomcat7:run
7. using rest client (eg: Postman chrome plugin ) 
    localhost:8080/puzzle-rest-api/rest/sudoku?query=3,1,6,5,x,8,4,x,x,5,2,x,x,x,x,x,x,x,x,8,7,x,x,x,x,3,1,x,x,3,x,1,x,x,8,x,9,x,x,8,6,3,x,x,5,x,5,x,x,9,x,6,x,x,1,3,x,x,x,x,2,5,x,x,x,x,x,x,x,x,7,4,x,x,5,2,x,6,3,x,x

