```toml
name = 'New Project'
id = '2ae36f56-bad8-4d7c-86dd-a8385f8fa55a'

[[environmentGroups]]
name = 'Default'
environments = ['Development']

[[apis]]
name = 'project API'
[apis.spring]
pattern = 'org.sampong.onlinebanking.*'
```

#### Variables

```json5
{
  development: {
    baseUrl: 'http://localhost:8080',
    username: 'System_admin',
    password: '123456'
  }
}
```
