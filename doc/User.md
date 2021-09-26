#### 1.Log in

**POST /user/login**

> request

Content-Type: application/json

```
{
	"username":"admin",
	"password":"admin",
}
```
> response

fail
```
{
    "status": 1,
    "msg": "wrong password"
}
```

success
```
{
    "status": 0,
    "data": {
        "id": 12,
        "username": "aaa",
        "email": "aaa@163.com",
        "phone": null,
        "role": 0,
        "createTime": 1479048325000,
        "updateTime": 1479048325000
    }
}
```


-------

#### 2.Register
**POST /user/register**

> request

```
{
	"username":"admin",
	"password":"admin",
	"email":"admin@qq.com"
}
```


> response

success
```
{
    "status": 0,
    "msg": "register success"
}
```


fail
```
{
    "status": 2,
    "msg": "user(email or username) existed"
}
```


#### 3.Get user information
**GET /user**

> request

```
No parameter
```
> response

success
```
{
    "status": 0,
    "data": {
        "id": 12,
        "username": "aaa",
        "email": "aaa@163.com",
        "phone": null,
        "role": 0,
        "createTime": 1479048325000,
        "updateTime": 1479048325000
    }
}
```

fail
```
{
    "status": 10,
    "msg": "please login before requiring user information"
}

```

------


#### 4.Log out
**POST /user/logout

> request

```
No parameter
```

> response

success

```
{
    "status": 0,
    "msg": "log out success"
}
```

fail
```
{
    "status": -1,
    "msg": "server error"
}
```
