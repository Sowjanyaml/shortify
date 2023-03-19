# Shortify - URL Shortener REST API

Url shortener service converts long url into short aliases to save space when sharing urls in messages, twitter, presentations etc.
REST API that will enable other users/ clients to send a URL and will receive hash to which this URL corresponds to.
The user will be able to resolve the full URL by passing the hash. 
We are currently generating [murmur3](https://en.wikipedia.org/wiki/MurmurHash) based hash function. 

Open [swagger](http://localhost:8080/swagger-ui/index.html) to see endpoints.


# How to use
+ With Docker

```sh
$ git clone https://github.com/Sowjanyaml/shortify.git

$ docker build --tag=shortify:latest . 
$ docker run -e "SPRING_PROFILES_ACTIVE=dev" -p8080:8080 shortify:latest
```
+ Without Docker:
```sh
$ git clone https://github.com/Sowjanyaml/shortify.git

```
* Open project with your favorite editor.
* Build and run Spring boot project
