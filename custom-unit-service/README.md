# Custom Unit Service
This is a custom unit service which extends the origin `custom-service`
## Introduction
Customer want to get unit addresses when they get unit by a user. To extend the origin `unit-service`,
we create this service.
For getUnits API, we use **restTemplate** to call the origin API and get unit list 
and then add address for each unit.
For other APIs we do not want to extend, we use `FeignClient` to keep the behavior of them.

example: 
Call GET http://localhost:10089/unitservice/v1/powertools/users/linda.wolf@rustic-hw.com/units
you will get unit list with addresses in it.

Call POST http://localhost:10089/unitservice/v1/powertools/users/linda.wolf@rustic-hw.com/units
you can create unit successfully and the API has the same behavior as the original one.