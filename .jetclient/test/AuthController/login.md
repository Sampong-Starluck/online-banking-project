```toml
name = 'login'
description = '/v1/api/web/auth/login'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/auth/login'
sortWeight = 1000000
id = '23a8ba7d-7be0-4f89-9864-22bb1242e00e'

[body]
type = 'JSON'
raw = '''
{
  "username": "System_admin",
  "password": "123456"
}'''
```
