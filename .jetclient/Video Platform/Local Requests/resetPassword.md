```toml
name = 'resetPassword'
description = 'user password reset feature'
method = 'POST'
url = '{{baseurl}}/user/reset-password'
sortWeight = 4000000
id = '288411a6-4e88-42e0-9ae5-af5c9eed0ef5'

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
