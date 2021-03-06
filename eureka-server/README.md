# Eureka Server
As a registry

# Config Server
This is a config server which enables other micro service get configuration through this server.
## How to config
1. Upload your .properties to https://github.wdf.sap.corp/CNACC/config-repo and named with the service name
2. Add dependency 

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```
3. Create a bootstrap.properties in your service.
```
spring.application.name=xxx
spring.cloud.config.uri=http://localhost:8761 <--config server url
spring.cloud.config.enabled=true <--config server url
management.endpoints.web.exposure.include=refresh <--allow runtime refresh
```
After this, you can start your service with config in config server. 
When you change the config, Call POST http://{ip}:{port}/actuator/refresh to refresh it.

## More info
The links below show how to support **multi tenancy** in config server
https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html#_pattern_matching_and_multiple_repositories
http://www.itmuch.com/spring-cloud/finchley-20/

