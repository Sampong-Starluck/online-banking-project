```toml
name = 'save'
description = '/v1/api/web/customer'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/customer'
sortWeight = 2000000
id = '91075719-89f0-4409-862d-e29a3573665e'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6IntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJTeXN0ZW1fYWRtaW5cIixcInJvbGVcIjpbXCJBRE1JTlwiXX0iLCJpYXQiOjE3NTgxOTA3NDQsImV4cCI6MTc1ODE5MTY0NH0.gyMVNG4jgwj9BZ18Kd0FoG_wccU43hKpHUvYv0HMLY4'

[body]
type = 'JSON'
raw = '''
{
  "id": 0,
  "firstName": "Joseph",
  "lastName": "Justin",
  "email": "example@gmail.otr",
  "phoneNumber": "012333444"
}'''
```
