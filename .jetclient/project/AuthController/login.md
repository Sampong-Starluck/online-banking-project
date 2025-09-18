```toml
name = 'login'
description = '/v1/api/web/auth/login'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/auth/login'
sortWeight = 1000000
id = '4910ea26-8f3c-4b79-a6b7-f007cec44412'

[body]
type = 'JSON'
raw = '''
{
  "username": "{{username}}",
  "password": "{{password}}"
} '''
```
