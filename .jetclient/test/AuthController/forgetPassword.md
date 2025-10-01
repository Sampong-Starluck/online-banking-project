```toml
name = 'forgetPassword'
description = '/v1/api/web/auth/forget-password'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/auth/forget-password'
sortWeight = 2000000
id = '78c06be5-b8e2-48e0-af34-f13c06a11f79'

[body]
type = 'JSON'
raw = '''
{
  "email": "",
  "password": ""
}'''
```
