extension point service for schema base:
1. prepare database.
    · you can create the schema and table by the sql.
    · default schema is 'tenant_default'
    · one tenant for one schema
    
2. you can operate the table by swagger-ui.
    · address http://localhost:8080/swagger-ui.html
    · the tenantId can exchange the schema

3. you need a running rabbit and a queue with name 'epc.service.queue
'.
    · you can send the message to the epc service by the message like:
    {
    "user": "customer1",
    "role": "role1",
    "tenantId": "tenant1",
    "environmentId": "env1"
    }
    · the tenantId decide which tenant/schema.