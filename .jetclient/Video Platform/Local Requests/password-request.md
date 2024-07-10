```toml
name = 'password-request'
description = 'user requests to reset their password'
method = 'POST'
url = '{{baseurl}}/user/password-request'
sortWeight = 5000000
id = 'a843fb81-faf9-4678-b886-1ad1825a03fc'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
{
  "email" : "codytechincoporation@gmail.com"
}'''
```
