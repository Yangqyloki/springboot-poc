# commerce-org micro service



## env prepare:
1. install mongodb and create db using ``MongoDB.sql``
2. install rabbitMQ
https://www.jianshu.com/p/14ffe0f3db94


## using steps:
1. get token for asagent from b2b commerce

2. generate certification for commerce or else there's security exception when unitservice try to call commerce api.

	**Note** 
	the certificate is issued for 127.0.0.1, so should run the commerce at local and add the cert to keystore at local.  the same cert installed at demo server(vm) doesn't work.
	https://wiki.hybris.com/display/cloudss/Smartedit+is+not+working+after+Hybris+1811+upgrade

	-------------------------------------------

	brief steps to trust the cert at local:

	- get cert from commerce server

		`openssl s_client -showcerts -connect localhost:9002`

		You will see details of the commerce certificate. You have to copy its content to notepad (from -----BEGIN CERTIFICATE----- to -----END CERTIFICATE-----):

	- create empty file named hybris.pem, then paste the content of copied certificate to that file

	- optional step. Check the "hybris.pem" file to check if it was copied properly:

		`sudo keytool -printcert -v -file hybris.pem`

	- Import new certificate to java keystore:

		```sudo keytool -import -alias hybris -file hybris.pem -keystore /usr/lib/jvm/java/jre/lib/security/cacerts```

	-------------------------------------------
## Application startup sequence

1. Start `config-server`
2. Start `eureka-server`
3. Start `unit-service`
4. Start `gateway`
5. (OPTIONAL) Star `user-service`
6. (OPTIONAL) Star `custom-unit-service`

### Use cases for unit-service

1. Get units for user with asagent token
	GET http://127.0.0.1:10086/unitservice/v1/powertools/users/linda.wolf@rustic-hw.com/units 

2. Create units for user with asagent token
	POST http://127.0.0.1:10086/unitservice/v1/powertools/users/linda.wolf@rustic-hw.com/units 

	```
	{
		"unitId":"unitIdTest",
		"unitName":"unitNameTest",
		"parentUnit":"Rustic",
		"approvalProcess":"approvalProcess"
	}
	```

3. Create user for unit (this step will trigger connect to rabbitMQ and create queue)
	http://127.0.0.1:10086/unitservice/v1/powertools/users/linda.wolf@rustic-hw.com/units/unitIdTest/customers
	```
	{
		"title":"mr",
		"firstName":"Test firstName",
		"lastName":"Test lastName",
		"email":"1@qq.com",
		"mobile":"13013001300",
		"parentUnit":"Rustic",
		"roles":["customergroup","b2bgroup"]
	}
	```
 - All APIs will validate if User is b2b admin, which is implemented by AOP
 - Create user for unit API will validate email and mobile in **Kyma**
 - It provides some logic before/after createCustomerForUnit, which is implemented by AOP


### Use cases for user-service

**Should wait step 5 above is run or else there's error for trying to get message queue which is not exist!!!**

### Use cases for custom-unit-service

1. Call GET method http://localhost:10088/unitservice/v1/powertools/users/linda.wolf@rustic-hw.com/units and you will see unit address in results 

### Use cases for gateway

1. Call GET http://localhost:10089/**unit-proxy**/unitservice/v1/powertools/users/linda.wolf@rustic-hw.com/units you can get response

2. Edit the .properties file https://github.wdf.sap.corp/CNACC/config-repo/blob/master/zuul-gateway.properties, change the line `zuul.routes.unit-service=/unit-proxy/**` to `zuul.routes.custom-unit-service=/unit-proxy/**`. Then call POST http://localhost:10089/actuator/refresh, and re-call http://localhost:10089/unit-proxy/unitservice/v1/powertools/users/linda.wolf@rustic-hw.com/units, you can get response with **address**
