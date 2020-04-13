cd ../frontend
docker run --rm --name angular-cli -v ${PWD}:/angular -w /angular node:12.0 /bin/bash -c "npm install -g @angular/cli@9.1; npm install; npm rebuild; ng build --baseHref=/new/"

Remove-Item -path '..\backend\src\main\resources\static\new\*'
Move-Item -Path './dist/frontend/*' -Destination '..\backend\src\main\resources\static\new'

cd ../backend
mvn clean install

:: Copy jar file to docker folder
Remove-Item -path '../docker/app/animeshop-0.0.1-SNAPSHOT.jar'
Move-Item -Path './target/animeshop-0.0.1-SNAPSHOT.jar' -Destination '../docker/app/animeshop-0.0.1-SNAPSHOT.jar'
::cd target

::copy animeshop-0.0.1-SNAPSHOT.jar ..\..\docker\app
cd ../docker

:: Create and push image
docker build -t ram2701/animeshop .
docker push ram2701/animeshop
