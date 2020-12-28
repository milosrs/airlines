# Airlines
Airlines recommender for HTEC test.

Admin user is created on application startup. Credentials are :
- username: admin
- password: admin

There is a file in the root, called Airlines.postman_collection.json
You can import this file into your own Postman and test all the URL-s if you wish.

If not, the most important urls are here:

* Login as admin: http://localhost:9001/api/auth/login - Use the body below to login. As a response, you will get a JWT token which you should copy into Authorization header.

```javascript
{
    "username" : "admin",
    "password" : "admin"
}
```

* Import CSV Data: http://localhost:9001/api/admin/import - You'll have to wait a bit for this to finish (should be less than 2 min)
* Show adjacency list: http://localhost:9001/api/admin/getAdjacencyList - Returns a huge string, which represents connections between nodes and their weights.
* Find path from city ID1 to city ID2 http://localhost:9001/api/pathfind/find - Example request body and response are below:

```javascript
{
    "srcCity" : 1369,
    "dstCity" : 1360
}
```

```javascript
{
    "paths": [
        {
            "sourceCity": "Ioannis Kapodistrias International Airport",
            "destinationCity": "Eleftherios Venizelos International Airport",
            "price": 58.4
        },
        {
            "sourceCity": "Eleftherios Venizelos International Airport",
            "destinationCity": "Ioannina Airport",
            "price": 73.94
        }
    ],
    "totalPrice": 132.34
}
```
