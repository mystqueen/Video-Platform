```toml
name = 'DownloadVideo A'
description = 'Admin downloading a file'
method = 'GET'
url = '{{baseurl}}/admin/getVideo?videoId=5'
sortWeight = 14000000
id = '18a63797-e818-4c33-a5f9-a3e794d83c78'

[[queryParams]]
key = 'videoId'
value = '5'

[[headers]]
key = 'Content-Type'
value = 'application/json'

[body]
type = 'JSON'
raw = '''
//{
//  "title" : "A Video",
//  "description": "this is a new video"
//}'''
```
