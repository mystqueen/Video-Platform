```toml
name = 'Resetpassword-request'
description = 'Admin requests to reset their password'
method = 'POST'
url = '{{baseurl}}/user/password-request'
sortWeight = 10500000
id = '54d142f3-7105-483b-ab76-4ac613626378'

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
