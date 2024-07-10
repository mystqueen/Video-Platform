```toml
name = 'VerifyUserEmail'
description = 'Check if the UUid token has expired or not'
method = 'GET'
url = '{{baseurl}}/user/verify-email?authToken=1b2eb477-0ad3-4638-9cf3-5d72443ad4bd'
sortWeight = 2000000
id = 'bf6258bb-e52e-4cc6-bf70-f21c1c07bd9b'

[[queryParams]]
key = 'authToken'
value = '1b2eb477-0ad3-4638-9cf3-5d72443ad4bd'
```
