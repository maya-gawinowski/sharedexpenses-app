# sharedexpenses-app

## Backend

### Run

To run backend move to the `backend` directory and run

```bash
npm run start
```

This will run a server listening on `localhost:3000`

One group with four users is hardcoded.

### Methods

Backend exposes methods (example body provided for post methods):

- POST `http://localhost:3000/groups/{groupId}/expenses`

```json
{
  "payerId": "1",
  "participantsIds": ["1", "2", "3", "4"],
  "amount": 100,
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

```json
{ "userIds": [1, 4] }
```

### To be implemented:

- Create a user
- (Optionally) optimize number of debts (quite complicated algorithm based on graph search, read more on [Splitwise simplification feature](https://medium.com/@mithunmk93/algorithm-behind-splitwises-debt-simplification-feature-8ac485e97688))
