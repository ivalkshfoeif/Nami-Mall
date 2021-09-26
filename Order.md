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
