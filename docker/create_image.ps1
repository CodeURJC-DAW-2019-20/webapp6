cd ../backend
docker run --rm --name maven -v ${PWD}:/usr/src -w /usr/src maven:3.6-jdk-8 mvn clean install
Remove-Item -path '../docker/app/santatecla-definitions-4.0.jar'
Move-Item -Path './target/animeshop-0.0.1-SNAPSHOT.jar' -Destination '../docker/app/animeshop-0.0.1-SNAPSHOT.jar'
cd ../docker
docker build -t ram2701/animeshop:latest .