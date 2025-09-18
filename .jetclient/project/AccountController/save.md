```toml
name = 'save'
description = '/v1/api/web/account'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/account'
sortWeight = 2000000
id = 'd58125f5-938e-48b0-ae78-09eea5249d87'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6IntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJTeXN0ZW1fYWRtaW5cIixcInJvbGVcIjpbXCJBRE1JTlwiXX0iLCJpYXQiOjE3NTgxOTA3NDQsImV4cCI6MTc1ODE5MTY0NH0.gyMVNG4jgwj9BZ18Kd0FoG_wccU43hKpHUvYv0HMLY4'

[body]
type = 'JSON'
raw = '''
{
  "id": 0,
  "issueDate": "2025-09-18T10:19:38.095Z",
  "expireDate": "2025-09-18T10:19:38.095Z",
  "balance": 0.0,
  "currency": "USD",
  "customer": {
    "id": 1
  }
}'''
```
