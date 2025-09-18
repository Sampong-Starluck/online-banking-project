```toml
name = 'register'
description = '/v1/api/web/user/register'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/user/register'
sortWeight = 1000000
id = 'a37d446c-881f-4173-af12-52c04f7416f9'

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
