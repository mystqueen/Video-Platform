```toml
name = 'user_signup'
description = 'register new user'
method = 'POST'
url = '{{baseurl}}/user/signup'
sortWeight = 1000000
id = '29fffd8d-45d7-4bfb-8f35-8f4745ad22f5'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[auth]
type = 'BEARER'

[body]
type = 'JSON'
raw = '''
{
  "email": "codytechincoporation@gmail.com",
  "password": "anita",
  "username": "Anita Ama"
}'''
```
