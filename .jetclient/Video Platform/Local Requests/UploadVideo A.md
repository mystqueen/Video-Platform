```toml
name = 'UploadVideo A'
description = 'Admin uploading a video file locally'
method = 'POST'
url = '{{baseurl}}/admin/upload'
sortWeight = 13000000
id = 'd63dec37-0ea1-46b7-bc8d-b751df90eb17'

[[queryParams]]
key = 'title'
value = 'A new video'
disabled = true

[[queryParams]]
key = 'file'
disabled = true

[[queryParams]]
key = 'description'
value = 'This is a video'
disabled = true

[[headers]]
key = 'Content-Type'
value = 'multipart/form-data'

[[body.formData]]
key = 'title'
value = 'A new video'

[[body.formData]]
key = 'description'
value = 'this is a video'

[[body.formData]]
type = 'FILE'
key = 'file'
value = 'C:\Users\DELL\Pictures\braille.jfif'

[[body.formData]]
key = 'adminId'
value = '1'
```
