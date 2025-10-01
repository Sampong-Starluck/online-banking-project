```toml
name = 'save'
description = '/v1/api/web/role'
method = 'POST'
url = '{{baseUrl}}/v1/api/web/role'
sortWeight = 2000000
id = 'd10aeef7-f217-45c3-8692-59f945b351c0'

[body]
type = 'JSON'
raw = '''
{
  "id": 0,
  "roleName": "",
  "roleDescription": "",
  "roleStatus": "",
  "status": false,
  "createdDate": "2025-10-01",
  "lastModifiedDate": "2025-10-01",
  "version": 0
}'''
```
