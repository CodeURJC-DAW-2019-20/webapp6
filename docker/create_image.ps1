cd ../backend
call mvn clean install

:: Copy jar file to docker folder
cd target
copy animeshop-0.0.1-SNAPSHOT.jar ..\..\docker\app
cd ../../docker

:: Create and push image
docker build -t ram2701/animeshop .
docker push ram2701/animeshop
