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
