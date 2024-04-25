# database-mongodb

- http://localhost:8080/swagger-ui/index.html#/

## step 0. set mongodb with local docker

```bash
$ docker-compose -f docker/docker-compose-mongodb.yml up
```

## step 1. repository save & find

```
// PersonController.http
POST {{host}}/person
GET {{host}}/person
```

<img width="1248" alt="image" src="https://github.com/Hyune-s-lab/kopring-workshop/assets/55722186/5f1573a5-0b6a-4f60-91cb-980bdab28765">

## step 2. set testcontainers for test

<img width="1148" alt="image" src="https://github.com/Hyune-s-lab/kopring-workshop/assets/55722186/3843d519-ddd9-42b0-9dc3-0cf57e1f6694">
