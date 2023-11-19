# Backend

## Run

To run backend move to the `backend` directory and run

```bash
npm run start
```

This will run a server listening on `localhost:3000`

One group with four users is hardcoded.


## API Methods using the RestClient
Kotlin code that allows to post data into the backend
### GET
- get Groups
  ```kotlin
  LaunchedEffect(Unit) {
        RestClient.instance.getGroups(object : GroupsCallback {
            override fun onSuccess(groups: List<Group>) {
                receivedGroups = groups
                Log.d("RestClient", "GET groups success $receivedGroups")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET groups error $error")
            }
        })
  }
  ```
- get Users
  ```kotlin
  LaunchedEffect(Unit) {
        RestClient.instance.getUsers(object : UsersCallback {
            override fun onSuccess(users: List<User>) {
                receivedUsers = users
                Log.d("RestClient", "GET users success $receivedUsers")
            }

            override fun onFailure(error: String) {
                Log.d("RestClient", "GET users error $error")
            }
        }, selectedGroup!!.id)
    }
  ```
- get expenses
  ```kotlin
    LaunchedEffect(Unit) {
            RestClient.instance.getExpenses(object : ExpensesCallback {
                override fun onSuccess(expenses: List<Expense>) {
                    receivedExpenses = expenses
                    Log.d("RestClient", "GET expenses success $receivedExpenses")
                }
    
                override fun onFailure(error: String) {
                    Log.d("RestClient", "GET expenses error $error")
                }
            }, selectedGroup!!.id)
        }
    ```
- get debts
  ```kotlin
  LaunchedEffect(Unit) {
          RestClient.instance.getDebts(object : DebtsCallback {
              override fun onSuccess(debts: List<Debt>) {
                  receivedDebts = debts
                  Log.d("RestClient", "GET debts success $receivedDebts")
              }
  
              override fun onFailure(error: String) {
                  Log.d("RestClient", "GET debts error $error")
              }
          }, group!!.id)
      }
  ```
### POST
- create a new group
  ```kotlin
  LaunchedEffect(Unit) {
    RestClient.instance.createGroups(listOf("1", "2"), "TestGroup", "EUR", "description" )
  }
  ```
  -> description is optional
## Methods as REST API request

Backend exposes methods (example body provided for post methods):

- POST `http://localhost:3000/groups/{groupId}/expenses`

```json
{
  "payerId": "1",
  "participantsIds": ["1", "2", "3", "4"],
  "amount": 100,
  "date": "2023-11-18T21:16:26.567Z",
  "description": "For beers"
}
```

- GET `http://localhost:3000/groups/{groupId}/expenses`
- GET `http://localhost:3000/groups/{groupId}/debts`

Debts are calculated automatically based on expenses, thus no POST debts method is possible.

- GET `http://localhost:3000/groups`
- POST `http://localhost:3000/groups`

```json
{
  "userIds": ["1", "3"],
  "name": "group 1",
  "description": "test group"
}
```

- DELETE `http://localhost:3000/groups/{groupId}`
- PUT `http://localhost:3000/groups/{groupId}` -> modify group (right now only by adding users)
- GET `http://localhost:3000/groups/{groupId}/users`

```json
{ "userIds": [1, 4] }
```

## To be implemented:

- Create a user
- (Optionally) optimize number of debts (quite complicated algorithm based on graph search, read more on [Splitwise simplification feature](https://medium.com/@mithunmk93/algorithm-behind-splitwises-debt-simplification-feature-8ac485e97688))
