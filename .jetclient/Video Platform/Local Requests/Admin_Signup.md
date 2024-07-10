```toml
name = 'Admin_Signup'
description = 'register new admin account'
method = 'POST'
url = '{{baseurl}}/admin/signup'
sortWeight = 8000000
id = '3a1749d7-2985-45be-a6cf-24563fca2aa6'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[auth]
type = 'BEARER'

[body]
type = 'JSON'
raw = '''
{
  "email": "codytechincoporatio@gmail.com",
  "password": "anita",
  "username": "Anita Ama"
}'''
```
