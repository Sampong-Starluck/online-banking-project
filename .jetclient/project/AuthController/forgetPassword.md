```toml
name = 'forgetPassword'
description = '/v1/api/web/auth/forget-password'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/auth/forget-password'
sortWeight = 2000000
id = '124f4dcf-c63e-448e-a486-c41102dcf0ce'

[body]
type = 'JSON'
raw = '''
{
  "email": "",
  "password": ""
}'''
```
