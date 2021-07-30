# SaaS Keycloak
Demo project for Spring Boot with Kotlin, Keycloak 11, Multi Tenant by Schema and Liquibase.
Forked from [CarinaPetravicius/keycloak-multi-tenant-schema](https://github.com/CarinaPetravicius/keycloak-multi-tenant-schema)

### Project Setup

- Run 'docker-compose up', to start Postgres and Keycloak in a docker container.
- Enter in 'http://localhost:8080/auth/' and click in 'Administration Console'.
- Enter with user name 'admin' and password '123456'. Does not set this password in production environment.

##### Create a new Realm instance:
- Name the Realm as 'company_master'.

##### Create a Realm Client:
- Name the Client as 'backend-api'.
- Set the Client Protocol as 'openid-connect'.
- Set the Access Type as 'confidential'.
- Set the Authorization Enabled to 'ON'.
- Set the Root URL to 'http://localhost:8090'.
- In Credentials Tab, copy the Secret id, and replace in application.properties in field 'keycloak.credentials.secret'.
- In the Roles Tab, create the roles: company and branch_office.

##### Create the Roles:
- Create the role named as 'company', and set the Composite Roles to 'ON'. In Client Roles, select the 'backend-api', and in Available Roles, select the 'company' to Add Selected.
- Create the role named as 'branch_office', and set the Composite Roles to 'ON'. In Client Roles, select the 'backend-api', and in Available Roles, select the 'branch_office' to Add Selected.

##### Create the Users:
- Create the user branch_office_1 and branch_office_2. In Credentials Tab, set the password to '123456' and set Temporary to 'OFF'. In Role Mappings Tab, in Available Roles(Realm Roles and Client Roles), select the 'branch_office' to Add Selected.

##### In Postgress Database
- Create the schema, named as master.

##### Custom User Attribute
- In Keycloak Users, select for example the user 'branch_office_1' and Edit.
- Go to Attributes.
- Create the key 'schema_name', and set the value 'schema_tenant_1', and Save.
- Go to Clients.
- Select for example the client 'backend-api'.
- Go to Mappers.
- Create the Mapper 'schema_name'. Select the 'User Attribute' as 'Mapper Type'. Select 'String' as 'Claim JSON Type'. Fill 'schema_name' in 'User Attribute' and 'Token Claim Name'.
- Now you can receive a custom user attribute in your access token.
- For user 'branch_office_2', do the same steps above, but in schema_name fill the value 'schema_tenant_2'. When you login with this user, the values will be saved in database schema: schema_tenant_2.

##### Start the application
- Start with maven clean install.

##### Authenticate to Get the Access Token
- By Postman you can authenticate with a POST to: http://localhost:8080/auth/realms/company_master/protocol/openid-connect/token
- In the Body select the x-www-form-urlencoded format, and send this keys and values:

| KEY           | VALUE                                |
| ------------- | ------------------------------------ |
| username      | branch_office_1                      |
| password      | 123456                               |
| grant_type    | password                             |
| client_id     | backend-api                          |
| client_secret | 1b5c82ed-00b2-4f37-852a-cbf3990fb372 |

- The value of client_secret, you must see the Secret id of the Realm Client generated in your Keycloak.
- After authenticate, get the 'access_token' generated in the response of the above request.
- Now you can do a POST in 'http://localhost:8090/v1/product' passing in the Header the KEY(Authorization), and the VALUE(Bearer access_token).
