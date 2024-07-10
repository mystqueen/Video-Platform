```toml
name = 'DeleteUseraccount'
description = "Amin can delete any user's account"
method = 'DELETE'
url = '{{baseurl}}/admin/delete-user'
sortWeight = 16000000
id = '07539e3a-abf5-45b2-a2cc-5ad0bf226b40'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
{
  "email": "codytechincoporation@gmail.com",
  "password": "anita",
  "username": "Anita Ama"
}'''
```
