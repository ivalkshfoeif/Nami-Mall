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
