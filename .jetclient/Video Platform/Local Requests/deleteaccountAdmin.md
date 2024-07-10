```toml
name = 'deleteaccountAdmin'
description = 'for an admin to delete the personal account'
method = 'DELETE'
url = '{{baseurl}}/admin/delete-admin'
sortWeight = 15000000
id = '7345125f-a98f-4fa0-a594-ca9eee357243'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
{
  "email": "codytechincoporation@gmail.com",
  "password": "aaaaaaaa",
  "username": "Anit Ama"
}'''
```
