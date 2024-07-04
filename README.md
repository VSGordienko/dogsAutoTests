To run the tests, you have to change the value of the variable `dogApiValue` in `application.properties` to your API key.
You can obtain it from the website https://thedogapi.com/ by registering for a free license.
After that you need to execute the following command:

`mvn test`

# Tests description

## FavouritesHappyFlowTest.java

AC1
### Favourites with one value

1. Add one dog to favourites using the POST request to /favourites with parameters:
   {
   "image_id": "BJa4kxc4X",
   "sub_id": "FirstDog"
   }
   And receive
   {
   "message": "SUCCESS",
   "id": {integerValue}
   }
   as a result
2. Verify that the dog has been added using the GET request to /favourites and receive
   [
   {
   "id": {id},
   "user_id": "dnog2i",
   "image_id": "BJa4kxc4X",
   "sub_id": "FirstDog",
   "created_at": "2024-05-10T12:33:48.000Z",
   "image": {
   "id": "BJa4kxc4X",
   "url": "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
   }
   }
   ]
   as a response
3. Verify that the dog has been added using the GET request to /favourites/{id} and receive
   {
   "id": {id},
   "user_id": "dnog2i",
   "image_id": "BJa4kxc4X",
   "sub_id": "FirstDog",
   "created_at": "2024-05-10T12:33:48.000Z",
   "image": {
   "id": "BJa4kxc4X",
   "url": "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
   }
   }

AC2
### Favourites with multiple values

1. Add one dog to favourites using the POST request to /favourites with parameters:
   {
   "image_id": "BJa4kxc4X",
   "sub_id": "FirstDog"
   }
   And receive
   {
   "message": "SUCCESS",
   "id": {integerValue}
   }
   as a result
2. Add the second dog to favourites using the POST request to /favourites with parameters:
   {
   "image_id": "WIf5o1E8h",
   "sub_id": "SecondDog"
   }
   And receive
   {
   "message": "SUCCESS",
   "id": {integerValue}
   }
   as a result
3. Verify that the dogs have been added using the GET request to /favourites and receive
   [
   {
   "id": {id1},
   "user_id": "dnog2i",
   "image_id": "BJa4kxc4X",
   "sub_id": "FirstDog",
   "created_at": "2024-05-10T12:47:48.000Z",
   "image": {
   "id": "BJa4kxc4X",
   "url": "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
   }
   },
   {
   "id": {id2},
   "user_id": "dnog2i",
   "image_id": "WIf5o1E8h",
   "sub_id": "SecondDog",
   "created_at": "2024-05-10T12:47:48.000Z",
   "image": {
   "id": "WIf5o1E8h",
   "url": "https://cdn2.thedogapi.com/images/WIf5o1E8h.jpg"
   }
   }
   ]
   as a response

## VotesHappyFlowTest.java

AC1
### Votes with one value

1. Add one dog to favourites using the POST request to /votes with parameters:
   {
   "image_id":"BJa4kxc4",
   "sub_id": "FirstDog",
   "value":10
   }
   And receive
   {
   "message": "SUCCESS",
   "id": {id},
   "image_id": "BJa4kxc4",
   "sub_id": "FirstDog",
   "value": 10,
   "country_code": {Country code}
   }
   as a result
2. Verify that the dog has been added using the GET request to /votes and receive
   [
   {
   "id": {id},
   "image_id": "BJa4kxc4X",
   "sub_id": "firstDog",
   "created_at": "2024-05-16T12:08:14.000Z",
   "value": 10,
   "country_code": {Country code},
   "image": {
   "id": "BJa4kxc4X",
   "url": "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
   }
   }
   ]
   as a response
3. Verify that the dog has been added using the GET request to /votes/{id} and receive
   {
   "id": {id},
   "image_id": "BJa4kxc4X",
   "sub_id": "firstDog",
   "created_at": "2024-05-16T12:08:14.000Z",
   "value": 10,
   "country_code": {Country code},
   "image": {
   "id": "BJa4kxc4X",
   "url": "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
   }
   }
   as a response

## FavouritesNegativeFlowTest.java

AC1
### Adding a dog to 'Favourites' without an image_id

1. Add one dog to favourites using the POST request to /favourites with parameters:
   {
   "sub_id": "FirstDog"
   }
   And receive
   "image_id" is required
   as a result

AC2
### Deleting a record with a non-existing id
1. Delete a dog by wrong id from favourites using the DELETE request to /favourites/fff
   And receive
   INVALID_ACCOUNT
   as a result

## VotesNegativeFlowTest.java

AC1
### Getting a record with a non-existing id
1. Get a dog by wrong id from votes using the GET request to /votes/fff
   And receive
   NO_VOTE_FOUND_MATCHING_ID
   as a result

AC2
### Deleting a record with a non-existing id
1. Delete a dog by wrong id from votes using the DELETE request to /votes/fff
   And receive
   NO_VOTE_FOUND_MATCHING_ID
   as a result