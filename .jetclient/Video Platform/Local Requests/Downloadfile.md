```toml
name = 'Downloadfile'
description = 'downloading a file'
method = 'GET'
url = '{{baseurl}}/file/download?filename=Java SpringBoot Upskill.txt'
sortWeight = 7000000
id = '54aa2820-63cb-4790-a974-b928509be0ec'

[[queryParams]]
key = 'filename'
value = 'Java SpringBoot Upskill.txt'

[[headers]]
key = 'Content-Type'
value = 'multipart/form-data'

[[body.formData]]
type = 'FILE'
key = 'filename'
value = 'Java SpringBoot Upskill.txt'
disabled = true
```
