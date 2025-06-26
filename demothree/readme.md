## Commands to run docker

- need to mvn clean and install to create the jar file first
- docker build -t [image-name]:[tag] .
    - Example: docker build -t demothree:1.0.0 .
    - -t → to create tag
- docker run -d -p [port-to-hit]:[port-exposed] --name [container-name] [image-name]:[tag]
    - Example: docker run -d -p 8082:8080 --name demothree-app demothree:1.0.0
    - -d → detach (berjalan terpisah)
    - -p → declare port
    - --name → declare name for the container

## Other commands:
docker --help
-> get list of commands
docker ps -a
-> check running container
docker stop [container-name]
-> stop specific container
docker start [container-name]
-> start specific container
docker rm [container-name]
-> delete specific container
docker rmi [image-name]:[tag]
-> delete specific image
docker images
-> check built images
docker push [username]/[image-name]:[tag]

**image-name harus sama dengan nama di docker hub**

### Using docker-compose.yaml
docker-compose build
