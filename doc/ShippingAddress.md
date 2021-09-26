#### 1.add new address

** POST /shippings


> request

```
receiverName=XXX
receiverPhone=010
receiverMobile=18688888888
receiverProvince=MA
receiverCity=Boston
receiverDistrict=XXXXX St
receiverAddress=xxxxx St
receiverZip=100000
```

> response

success

```
{
    "status": 0,
    "msg": " creating new address success",
    "data": {
        "shippingId": 28
    }
}
```

fail
```
{
    "status": 1,
    "msg": "fail to create new address"
}
```


------


#### 2.delete address

**DELETE /shippings/{shippingId}

DELETE /shippings/28

> request

```
shippingId
```

> response

success

```
{
    "status": 0,
    "msg": "delete the address success"
}
```

fail
```
{
    "status": 1,
    "msg": "fail to delete the address"
}
```


------


#### 3.update the address

**PUT /shippings/{shippingId}

> request

```
receiverName=XXX
receiverPhone=010
receiverMobile=18688888888
receiverProvince=MA
receiverCity=Boston
receiverDistrict=XXXXX St
receiverAddress=xxxxx St
receiverZip=100000
```

> response

success

```
{
    "status": 0,
    "msg": "update the address success"
}
```

fail
```
{
    "status": 1,
    "msg": "fail to update the address"
}
```


------


####4.list all addresses

**GET /shippings**

> request

```
pageNum(default 1),pageSize(default 10)
```

> response

success

```
{
    "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "size": 2,
        "orderBy": null,
        "startRow": 1,
        "endRow": 2,
        "total": 2,
        "pages": 1,
        "list": [
            {
                "id": 4,
                "userId": 13,
                "receiverName": XXX,
                "receiverPhone": 010,
                "receiverMobile": 18688888888,
                "receiverProvince": "MA",
                "receiverCity": "Boston",
                "receiverDistrict":"XXXXX St",
                "receiverAddress:: "xxxxx St",
                "receiverZip": "100000",
                "createTime": 1485066385000,
                "updateTime": 1485066385000
            },
            {
                "id": 5,
                "userId": 13,
                "receiverName": XXX,
                "receiverPhone": 010,
                "receiverMobile": 18688888888,
                "receiverProvince": "MA",
                "receiverCity": "Boston",
                "receiverDistrict":"XXXXX St",
                "receiverAddress:: "xxxxx St",
                "receiverZip": "100000",
                "createTime": 1485066392000,
                "updateTime": 1485075875000
            }
        ],
        "firstPage": 1,
        "prePage": 0,
        "nextPage": 0,
        "lastPage": 1,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ]
    }
}
```

fail
```
{
    "status": 1,
    "msg": "please login first"
}
```
 
