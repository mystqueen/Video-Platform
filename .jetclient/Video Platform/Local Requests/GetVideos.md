```toml
name = 'GetVideos'
description = 'get list of videos'
method = 'GET'
url = '{{baseurl}}/admin/getVideos'
sortWeight = 19000000
id = '41606b1d-2b10-476d-98de-3a464e2aac99'

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
