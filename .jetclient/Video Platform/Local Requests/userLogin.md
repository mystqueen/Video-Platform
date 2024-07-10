```toml
name = 'userLogin'
description = 'user login feature'
method = 'POST'
url = '{{baseurl}}/user/login'
sortWeight = 3000000
id = 'ff2d2fa1-49c1-4cbf-bf27-2406ed1f695b'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
{
  "email": "codytechincoporation@gmail.com",
  "password": "nanny"
}'''
```
