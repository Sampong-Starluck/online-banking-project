```toml
name = 'transfer'
description = '/v1/api/web/transaction/transfer'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/transaction/transfer'
sortWeight = 1000000
id = '81de86d4-fd88-4f26-a8d9-fb75d2731a67'

[auth.bearer]
token = 'eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6IntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJTeXN0ZW1fYWRtaW5cIixcInJvbGVcIjpbXCJBRE1JTlwiXX0iLCJpYXQiOjE3NTkzMjA0MTEsImV4cCI6MTc1OTMyMTMxMX0.tSoYsb9jm2D7E_w_pfocrl7AhSC_uHektqMXATZyC54'

[body]
type = 'JSON'
raw = '''
{
  "srcAccountId": 1,
  "targetAccountId": 2,
  "balance": 1000,
  "currency": "USD",
  "type": "TRANSFER"
}'''
```
