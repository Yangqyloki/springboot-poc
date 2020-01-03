# Unit Service
## Introduction
Dummy unit service 

## DB
mongo

## APIs
 - getUnits
   Get all units for a b2b admin
 - createUnit
   Create a unit for a b2b admin
 - createCustomerForUnit
   Create customer for a b2b unit
 
 ## Tech points
  - All APIs will validate if User is b2b admin, which is implemented by AOP
  - Using restTemplate to call commerce-suite API to validate b2b admin
  - Create user for unit API will validate email and mobile in Kyma
  - It provides some logic before/after createCustomerForUnit, which is implemented by AOP
