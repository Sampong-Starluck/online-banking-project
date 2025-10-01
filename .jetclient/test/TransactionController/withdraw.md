```toml
name = 'withdraw'
description = '/v1/api/web/transaction/withdraw'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/transaction/withdraw'
sortWeight = 5000000
id = 'f232ab2a-367d-4d2a-a9c4-fd1253d25d5d'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6IntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJTeXN0ZW1fYWRtaW5cIixcInJvbGVcIjpbXCJBRE1JTlwiXX0iLCJpYXQiOjE3NTkzMTg4MTksImV4cCI6MTc1OTMxOTcxOX0.Rseb6RM_dI-ScdyfE3ow79ztHyJ9yG2_JEZAZBxjiqU'

[body]
type = 'JSON'
raw = '''
{
  "accountId": 1,
  "currency": "USD",
  "balance": 1000
}'''
```
