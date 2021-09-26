# Nami-Mall
A project that clones Xiaomi Mall back end. The system can handle front-end requests to create users, log in, creat ,read, update and delete shopping cart, place orders and make real payment by AliPay and WeChatPay.

<img width="1526" alt="1632458509(1)" src="https://user-images.githubusercontent.com/61750044/134619342-fabde536-dedb-4f03-962d-856ae42b5b7b.png">

# API DOC
## [Order](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/Order.md)
#### 1.create an order

** POST /orders

> request

```
shippingId
```

> response

success

```
{
    "status": 0,
    "data": {
        "orderNo": 1291136461000,
        "payment": 2999.11,
        "paymentType": 1,
        "postage": 0,
        "status": 10,
        "paymentTime": null,
        "sendTime": null,
        "endTime": null,
        "closeTime": null,
        "createTime": 1291136461000,
        "orderItemVoList": [
            {
                "orderNo": 1291136461000,
                "productId": 2,
                "productName": "oppo R8",
                "productImage": "mainimage.jpg",
                "currentUnitPrice": 2999.11,
                "quantity": 1,
                "totalPrice": 2999.11,
                "createTime": null
            }
        ],
        "shippingId": 5,
        "shippingVo": {
                "id": 4,
                "userId": 13,
                "receiverName": "xxx",
                "receiverPhone": "010",
                "receiverMobile": "18688888888",
                "receiverProvince": "MA",
                "receiverCity": "Bostom",
                "receiverDistrict": "XXX",
                "receiverAddress": "XXX",
                "receiverZip": "100000",
                "createTime": 1485066385000,
                "updateTime": 1485066385000
            }
    }
}
```

fail
```
{
    "status": 1,
    "msg": "fail to create an order"
}
```

------

#### 2.list all orders

** GET /orders

> request

```
pageSize(default=10)
pageNum(default=1)
```

order status:0-cancelled-10-unpaid，20-paid，40-shipped，50-success，60-close

> response

success

```
{
  "status": 0,
  "data": {
    "pageNum": 1,
    "pageSize": 3,
    "size": 3,
    "orderBy": null,
    "startRow": 1,
    "endRow": 3,
    "total": 16,
    "pages": 6,
    "list": [
      {
        "orderNo": 1291136461000,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "ONLINE_PAY",
        "postage": 0,
        "status": 10,
        "statusDesc": "UMPAID",
        "paymentTime": "2010-02-11 12:27:18",
        "sendTime": "2010-02-11 12:27:18",
        "endTime": "2010-02-11 12:27:18",
        "closeTime": "2010-02-11 12:27:18",
        "createTime": "2010-01-23 16:04:36",
        "orderItemVoList": [
          {
            "orderNo": 1291136461000,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2010-01-23 16:04:36"
          }
        ],
        "shippingId": 5,
        "receiverName": "XXX",
        "shippingVo": null
      },
      {
        "orderNo": 1291136461001,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "ONELINE_PAY",
        "postage": 0,
        "status": 10,
        "statusDesc": "XXX",
        "paymentTime": "2010-02-11 12:27:18",
        "sendTime": "2010-02-11 12:27:18",
        "endTime": "2010-02-11 12:27:18",
        "closeTime": "2010-02-11 12:27:18",
        "createTime": "2010-01-23 16:04:35",
        "orderItemVoList": [
          {
            "orderNo": 1291136461001,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2010-01-23 16:04:35"
          }
        ],
        "shippingId": 5,
        "receiverName": "XXX",
        "shippingVo": null
      },
      {
        "orderNo": 1291136461002,
        "payment": 2999.11,
        "paymentType": 1,
        "paymentTypeDesc": "ONLINE_PAY",
        "postage": 0,
        "status": 10,
        "statusDesc": "XXX",
        "paymentTime": "2010-02-11 12:27:18",
        "sendTime": "2010-02-11 12:27:18",
        "endTime": "2010-02-11 12:27:18",
        "closeTime": "2010-02-11 12:27:18",
        "createTime": "2010-01-23 16:04:35",
        "orderItemVoList": [
          {
            "orderNo": 1291136461002,
            "productId": 2,
            "productName": "oppo R8",
            "productImage": "mainimage.jpg",
            "currentUnitPrice": 2999.11,
            "quantity": 1,
            "totalPrice": 2999.11,
            "createTime": "2010-01-23 16:04:35"
          }
        ],
        "shippingId": 5,
        "receiverName": "XXX",
        "shippingVo": null
      }
    ],
    "firstPage": 1,
    "prePage": 0,
    "nextPage": 2,
    "lastPage": 6,
    "isFirstPage": true,
    "isLastPage": false,
    "hasPreviousPage": false,
    "hasNextPage": true,
    "navigatePages": 8,
    "navigatepageNums": [
      1,
      2,
      3,
      4,
      5,
      6
    ]
  }
}
```

fail
```
{
  "status": 10,
  "msg": "please login first"
}


or

{
  "status": 1,
  "msg": "You are not authorized to do this action"
}



```

------

#### 3.Order detail

** GET /orders/{orderNo}

> request

```
orderNo
```

> response

success

```
{
  "status": 0,
  "data": {
    "orderNo": 1291136461000,
    "payment": 30000.00,
    "paymentType": 1,
    "paymentTypeDesc": "ONLINE_PAY",
    "postage": 0,
    "status": 10,
    "statusDesc": "UNPAID",
    "paymentTime": "",
    "sendTime": "",
    "endTime": "",
    "closeTime": "",
    "createTime": "2010-11-30 22:23:49",
    "orderItemVoList": [
      {
        "orderNo": 1291136461000,
        "productId": 1,
        "productName": "iphone7",
        "productImage": "mainimage.jpg",
        "currentUnitPrice": 10000.00,
        "quantity": 1,
        "totalPrice": 10000.00,
        "createTime": "2010-11-30 22:23:49"
      },
      {
        "orderNo": 1291136461000,
        "productId": 2,
        "productName": "oppo R8",
        "productImage": "mainimage.jpg",
        "currentUnitPrice": 20000.00,
        "quantity": 1,
        "totalPrice": 20000.00,
        "createTime": "2010-11-30 22:23:49"
      }
    ],
    "shippingId": 3,
    "receiverName": "XXX",
    "shippingVo": {
      "receiverName": "XXX",
      "receiverPhone": "0100",
      "receiverMobile": "186",
      "receiverProvince": "MA",
      "receiverCity": "Boston",
      "receiverDistrict": "XXX",
      "receiverAddress": "XXX",
      "receiverZip": "100000"
    }
  }
}

```

fail
```
{
  "status": 1,
  "msg": "order not found"
}
```

------

#### 4.Cancel orders

** PUT /orders/{orderNo}

> request

```
orderNo
```

> response

success

```
{
  "status": 0
}

```

fail
```
{
  "status": 1,
  "msg": "The user do not have the order"
}

or
{
  "status": 1,
  "msg": "The order is paid already, refund is not supported"
}
```

------

## [Category](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/Category.md)
#### 1.All categories

**GET /categories**

> request

Login not required

> response


success
```
{
    "status": 0,
    "data": [{
        "id": 100001,
        "parentId": 0,
        "name": "Household appliances",
        "sortOrder": 1,
        "subCategories": [{
            "id": 100006,
            "parentId": 100001,
            "name": "refrigerator",
            "sortOrder": 2,
            "subCategories": [{
                "id": 100040,
                "parentId": 100006,
                "name": "Imported refrigerator",
                "sortOrder": 1,
                "subCategories": []
            }]
        },  {
        "id": 100005,
        "parentId": 0,
        "name": "drinks",
        "sortOrder": 1,
        "subCategories": [{
            "id": 100026,
            "parentId": 100005,
            "name": "Liquor",
            "sortOrder": 1,
            "subCategories": []
        }, {
            "id": 100027,
            "parentId": 100005,
            "name": "Wine",
            "sortOrder": 1,
            "subCategories": []
        }]
    }]
}
```

## [ShippingAddress](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/ShippingAddress.md)
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
 

## [ShoppingCart](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/ShoppingCart.md)

## [User](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/User.md)

# Version
Java 1.8
MySQL 5.7

