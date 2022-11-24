# Party contact storing application

Application is built using Spring boot 2.7.5 and Java 17 versions

## Source code

https://github.com/Justinasin/party-contact-storing


## Building & running

**Set up application**

Before starting to build application, need to set up:

- development/config.env if you are going to run application in Docker container or locally **without any spring profile**
- application-local.yml file if you are going to run application locally with spring profile "local"

**Using Docker**

- Navigate to root catalog of the project
- Build docker image: docker build -t image_name:tag . , e.g. *docker build -t party-contact-store:latest
  .*
- Run docker container with built image: docker run [docker_image] , e.g. *docker run -d --env-file
  development/config.env -p 8080:8080 party-contact-store:latest*  
  `--env-file` stands for application configuration file  
  `-d` runs container in background and prints container ID  
  `-p` publishes a container's port(s) to the host

**Using development IDE IntellIJ**

- Set up Run/Debug configurations
- Add VM options `-Dspring.profiles.active=local`
- Set the main class of application

## Deploying & running

For deployment you will need:

- Generated `jar` file from target catalog
- Docker file
- config.env file

When we have these files, we are ready to run the application

- Navigate to the catalog of the project where your files are located
- Build docker image: docker build -t image_name:tag . , e.g. *docker build -t party-contact-store:latest
  .*
- Run docker container with built image: docker run [docker_image] , e.g. *docker run -d --env-file
  config.env -p 8080:8080 party-contact-store:latest*

Make sure that path of `--env-file` is correct. If your `config.env` file is in the same catalog
as `Docker`
file, then `--env-file` should look like *--env-file config.env*

`Docker` file has attribute `JAR_FILE`, which is set to *target/*.jar* for local deployment  
After deploying `Docker` file to the server, change the path of `JAR_FILE` to *.jar* while all files
,which is necessary for deployment, will be in the same catalog.

## Logging

**Local development**

All log files are located in `source_code_root/logs` folder

**Docker container**

See logs directly in container  
`docker ps` - find your container ID  
`docker logs your_container_id` - see logs of your container  

Logs in Docker container are located in `/usr/app/logs`, need to mount a volume in your local environment
to mirror logs from Docker container to your machine or server  
When running Docker container specify volume, e.g.: 
`docker run --env-file config.env -v /home/user/application/logs:/usr/app/logs -p 8080:8080 party-contact-store:latest`

## Testing

Application is covered unit tests 

## Features

**Database**

Application is using H2 database which is lightweight in-memory database  
After application is running, database is accessible: url:port/h2-console,
e.g. *http://localhost:8080/h2-console*  
Database is enriched with primary data from `resources/people.csv` file

**Rest services**

```sh
/parties  
Service returns all parties from the database, and it is pageable.

Example: 
curl --location --request GET 'localhost:8080/parties' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--header 'Cookie: JSESSIONID=B5A41E4584F9B897621060F8C2CFA10A'
```

```sh  
/parties/{firstName}  
Service returns a party filtered by first name from the table  
Attribute firstName can not be empty and maximum length is 50 

Responses:  
200 OK - party was found  
404 NOT FOUND - party with the following first name does not exist  

Example:
curl --location --request GET 'localhost:8080/parties/Harriette' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--header 'Cookie: JSESSIONID=B5A41E4584F9B897621060F8C2CFA10A'
```

**Security**  

Rest services are secured using Basic Auth  
Default credentials in `conf.env` file are `admin, admin`

# Front end   

## Building & running

- Navigate to *root_catalog_of_project/frontend/party-list*
- Run `ng build` in terminal
- Run `ng serve` in terminal
- Head to http://localhost:4200