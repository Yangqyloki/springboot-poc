# Zuul Gateway
## Introduction
### GateWayController
A demo controller which is to validate the gateway service can also define a REST controller.
The controller calls 2 APIs, one comes from unit-service and the other from commerce-suite.
Then the controller combines 2 results together as the API response.
```
example: http://localhost:10089/gateway-forward/v1/powertools/users/linda.wolf@rustic-hw.com/units
```

### UnitServiceFallbackProvider
This provider will screen out **all** errors from `unit-service` and return a new resopnse.
Note that does not fit us because it screen out **all** errors.
```
example: Add the @Component above the class and call http://localhost:10089/unit-proxy/xxx
```

### ReplaceRouteFilter
When 2 services config the same path and in `replace.route.filter.map`, the targetService will replace the
sourceService when calling sourceService API.
