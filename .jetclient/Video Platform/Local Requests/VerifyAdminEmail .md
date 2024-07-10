```toml
name = 'VerifyAdminEmail '
description = 'Check if the UUid token has expired or not'
method = 'GET'
url = '{{baseurl}}/user/verify-email?authToken=1f407c18-e33e-48df-8494-5eb9fba688be'
sortWeight = 9000000
id = 'c6fa4060-fb65-4ff2-b3d3-7ded94637008'

[[queryParams]]
key = 'authToken'
value = '1f407c18-e33e-48df-8494-5eb9fba688be'
```
