```toml
name = 'AdminLogin'
description = 'Admin login feature'
method = 'POST'
url = '{{baseurl}}/admin/login'
sortWeight = 10000000
id = '3d9c1cd5-bc6c-4797-a7d0-c05cd19917e1'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
{
  "email": "codytechincoporation@gmail.com",
  "password": "aaaaaaaa"
}'''
```
