```toml
name = 'deposit'
description = '/v1/api/web/transaction/deposit'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/transaction/deposit'
sortWeight = 4000000
id = '3d5abac8-0292-4cd9-b1a3-bc39a578dee6'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6IntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJTeXN0ZW1fYWRtaW5cIixcInJvbGVcIjpbXCJBRE1JTlwiXX0iLCJpYXQiOjE3NTkzMjA5OTcsImV4cCI6MTc1OTMyMTg5N30.AvTt7mz4i9Oir0B_MCQuUX5nQ505Tq8T0roh0mpYxR0'

[body]
type = 'JSON'
raw = '''
{
  "accountId": 1,
  "currency": "USD",
  "balance": 10000
}'''
```
