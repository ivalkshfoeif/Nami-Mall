- [Nami-Mall](#nami-mall)
- [API document](#api-document)
  * [[Order](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/Order.md)](#-order--https---githubcom-ivalkshfoeif-nami-mall-blob-main-doc-ordermd-)
      - [1.Create an order](#1create-an-order)
      - [2.List all orders](#2list-all-orders)
      - [3.Order detail](#3order-detail)
      - [4.Cancel orders](#4cancel-orders)
  * [[Category](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/Category.md)](#-category--https---githubcom-ivalkshfoeif-nami-mall-blob-main-doc-categorymd-)
      - [1.All categories](#1all-categories)
  * [[ShippingAddress](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/ShippingAddress.md)](#-shippingaddress--https---githubcom-ivalkshfoeif-nami-mall-blob-main-doc-shippingaddressmd-)
      - [1.Add new address](#1add-new-address)
      - [2.Delete address](#2delete-address)
      - [3.Update the address](#3update-the-address)
  * [[ShoppingCart](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/ShoppingCart.md)](#-shoppingcart--https---githubcom-ivalkshfoeif-nami-mall-blob-main-doc-shoppingcartmd-)
      - [1.Shopping cart list](#1shopping-cart-list)
      - [2.Add new items](#2add-new-items)
      - [3.Update shopping cart](#3update-shopping-cart)
      - [4.Remove certain item from shopping cart](#4remove-certain-item-from-shopping-cart)
      - [5.Select all items](#5select-all-items)
      - [6.Unselect all](#6unselect-all)
      - [7.Calculate the total quantity of items selected](#7calculate-the-total-quantity-of-items-selected)
  * [[User](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/User.md)](#-user--https---githubcom-ivalkshfoeif-nami-mall-blob-main-doc-usermd-)
      - [1.Log in](#1log-in)
      - [2.Register](#2register)
      - [3.Get user information](#3get-user-information)
      - [4.Log out](#4log-out)
- [Version](#version)


# Nami-Mall
A project that clones Xiaomi Mall back end. The system can handle front-end requests to create users, log in, creat ,read, update and delete shopping cart, place orders and make real payment by AliPay and WeChatPay.

<img width="1526" alt="1632458509(1)" src="https://user-images.githubusercontent.com/61750044/134619342-fabde536-dedb-4f03-962d-856ae42b5b7b.png">

# API document
## [Order](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/Order.md)
#### 1.Create an order

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

#### 2.List all orders

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
#### 1.Add new address

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


#### 2.Delete address

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


#### 3.Update the address

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
#### 1.Shopping cart list

** GET /carts

> request

```
No parameter, login required
```

> response

success

```

{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "productId": 1,
                "quantity": 1,
                "productName": "iphone7",
                "productSubtitle": "Black Fridy promotion",
                "productMainImage": "mainimage.jpg",
                "productPrice": 7199.22,
                "productStatus": 1,
                "productTotalPrice": 7199.22,
                "productStock": 86,
                "productSelected": true,
            },
            {
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "promotion ongoing",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productSelected": false,
            }
        ],
        "selectedAll": false,
        "cartTotalPrice": 10198.33,
        "cartTotalQuantity": 2
    }
}

```

fail
```
{
    "status": 10,
    "msg": "please login first"
}
```


------


#### 2.Add new items

** POST /carts

> request

```
productId
selected: true
```

Each time this interface is called, the quantity will add up to one

> response

success

```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "productId": 1,
                "quantity": 12,
                "productName": "iphone7",
                "productSubtitle": "Black Friday promotion",
                "productMainImage": "mainimage.jpg",
                "productPrice": 7199.22,
                "productStatus": 1,
                "productTotalPrice": 86390.64,
                "productStock": 86,
                "productSelected": true
            },
            {
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "promotion ongoing",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productSelected": true
            }
        ],
        "selectedAll": true,
        "cartTotalPrice": 89389.75,
        "cartTotalQuantity": 13
    }
}
```

fail
```
{
    "status": 10,
    "msg": "please login first"
}
```


------





#### 3.Update shopping cart

** PUT /carts/{productId}

> request

```
quantity //not necessary
selected: true //not necessary
```

> response


success

success

```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "productId": 1,
                "quantity": 12,
                "productName": "iphone7",
                "productSubtitle": "Black Friday promotion",
                "productMainImage": "mainimage.jpg",
                "productPrice": 7199.22,
                "productStatus": 1,
                "productTotalPrice": 86390.64,
                "productStock": 86,
                "productSelected": true
            },
            {
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "promotion ongoing",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productSelected": true
            }
        ],
        "selectedAll": true,
        "cartTotalPrice": 89389.75,
        "cartTotalQuantity": 13
    }
}
```

fail
```
{
    "status": 10,
    "msg": "please login first"
}
```


------



#### 4.Remove certain item from shopping cart

** DELETE /carts/{productId}

> request

```
productId
```

> response

success

```
{
    "status": 0,
    "data": {
        "cartProductVoList": [
            {
                "productId": 2,
                "quantity": 1,
                "productName": "oppo R8",
                "productSubtitle": "promotion ongoing",
                "productMainImage": "mainimage.jpg",
                "productPrice": 2999.11,
                "productStatus": 1,
                "productTotalPrice": 2999.11,
                "productStock": 86,
                "productSelected": true
            }
        ],
        "selectedAll": true,
        "cartTotalPrice": 2999.11,
        "cartTotalQuantity": 1
    }
}
```

fail
```
{
    "status": 10,
    "msg": "please login first"
}
```


------

#### 5.Select all items

** PUT /carts/selectAll

> request

```
login required
```

> response

success

same as 1.Shopping cart list

------

#### 6.Unselect all

** PUT /carts/unSelectAll

> request

```
login required
```

> response

success

same as 1.Shopping cart list

------

#### 7.Calculate the total quantity of items selected

** GET /carts/products/sum

> request

```
login required
```

> response

```
{
    "status": 0,
    "data": 2
}
```


## [User](https://github.com/ivalkshfoeif/Nami-Mall/blob/main/doc/User.md)
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


# Version
Java 1.8
MySQL 5.7

