```toml
name = 'AdminResetPassword'
description = 'admin password reset feature'
method = 'POST'
url = '{{baseurl}}/user/reset-password'
sortWeight = 11000000
id = 'bf427010-bd4c-48f6-ae91-18876bf51896'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
{
  "resetToken" : "7f8d7b6e-b2db-455b-a4ee-253df0701574",
  "newPassword": "nanny"
}'''
```
