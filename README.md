
This project has RESTful endpoints to add a station, remove a station, update a station, search stations by station ID or name, search HD enabled stations
Docker image(station, tag:v1) has been implemented for this RESTful service which can be run and be accessed on port 9090.
This project uses an in  memory H2 database.

Build command: ./gradlew clean build

Create jar: ./gradlew clean build jar

jdbc url: jdbc:h2:mem:testdb

docker run: docker run -p 9090:9090 station:v1

Jacoco test coverage report is in $base_dir/build/reports/jacoco/test/html/
