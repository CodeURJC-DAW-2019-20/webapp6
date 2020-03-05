cd ../backend
docker run --rm --name maven -v ${PWD}:/usr/src -w /usr/src maven:3.6-jdk-8 call mvn clean install

:: Copy jar file to docker folder

copy target\itinerary-0.0.1-SNAPSHOT.jar ..\docker\app
cd ../docker

:: Create and push image
docker build -t ram2701/animeshop .
docker push ram2701/animeshop
