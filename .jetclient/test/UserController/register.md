```toml
name = 'register'
description = '/v1/api/web/user/register'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/user/register'
sortWeight = 1000000
id = '10305305-8198-4c84-bb92-12de7944ed02'

[body]
type = 'JSON'
raw = '''
{
  "firstName": "",
  "lastName": "",
  "email": "",
  "phoneNumber": "",
  "admin": false,
  "roles": [
    0
  ]
}'''
```
