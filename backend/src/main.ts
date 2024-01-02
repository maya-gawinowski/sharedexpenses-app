/* eslint-disable prettier/prettier */
import express, { Request, Response } from 'express';
import { PORT } from './config';

import morgan from 'morgan';
import { Group } from './group';
import { Expense } from './model';
import { Datasource } from './datasource';
import { User } from './user';
import { authenticate } from './user-authentication/Authenticate';
import passportConfig from './user-authentication/Passport';

passportConfig.initialiseJWTStrategy();

const app = express();
app.use(morgan('combined'));
app.use(express.json());
app.listen(PORT, () => {
  console.log('Server listening on port:', PORT);
});

const datasource = new Datasource();
let groups = datasource.groups;
const users = datasource.users;

app.get('/status', (req: Request, res: Response) => {
  const status = {
    Status: 'Running',
  };
  res.send(status);
});

app.get(
  '/groups/:groupId/debts',
  authenticate,
  (req: Request, res: Response) => {
    const groupId = req.params.groupId;
    const group = groups.find((g) => g.getId() === groupId);
    if (!group) {
      res.status(404).send('Group not found');
    }
    res.json(group.getDebts());
  },
);

app.get(
  '/groups/:groupId/expenses',
  authenticate,
  (req: Request, res: Response) => {
    const groupId = req.params.groupId;
    const group = groups.find((g) => g.getId() === groupId);
    if (!group) {
      res.status(404).send('Group not found');
    }
    res.json(group.getExpenses());
  },
);

app.post(
  '/groups/:groupId/expenses',
  authenticate,
  (req: Request, res: Response) => {
    const expense: Expense = req.body;
    const groupId = req.params.groupId;
    const group = groups.find((g) => g.getId() === groupId);
    if (!group) {
      res.status(404).send('Group not found');
    }
    group.addExpense(expense);
    res.json(group.getExpenses());
  },
);

app.get('/groups', authenticate, (req: Request, res: Response) => {
  res.json(groups);
});

app.post('/groups', authenticate, (req: Request, res: Response) => {
  const groupData = req.body;
  if (!groupData.userIds || !groupData.name) {
    res.status(404).send('Users or group name missing');
    return;
  }
  const newUsers: User[] = [];
  for (const uid of groupData.userIds) {
    const user = users.find((u) => u.id === uid);
    if (!user) {
      res.status(404).send(`User Id ${uid} not found`);
      return;
    }
    newUsers.push(user);
  }
  const group = new Group(
    newUsers,
    groupData.name,
    groupData.currency,
    groupData.description,
  );
  groups.push(group);
  res.status(201).json(group);
});

app.get('/groups/:groupId', authenticate, (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
  }
  res.json(group);
});

app.put('/groups/:groupId', authenticate, (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const addUserIds = req.body.addUserIds;
  const removeUserIds = req.body.removeUserIds;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
    return;
  }
  const newUsers: User[] = [];
  if (addUserIds) {
    for (const uid of addUserIds) {
      if (group.getUsers().find((existing) => existing.id === uid)) {
        console.log(`User Id ${uid} already exist in the group`);
        continue;
      }
      const user = users.find((u) => u.id === uid);
      if (!user) {
        res.status(404).send(`User Id ${uid} not found`);
        return;
      }
      newUsers.push(user);
    }
    group.addUsers(newUsers);
  }
  if (removeUserIds) {
    for (const uid of removeUserIds) {
      const userIndex = users.findIndex((u) => u.id === uid);
      if (!userIndex) {
        res.status(404).send(`User Id ${uid} not found`);
        return;
      }
      users.splice(userIndex, 1);
    }
  }
  res.status(200).json(group.getUsers());
});

app.delete('/groups/:groupId', authenticate, (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
    return;
  }
  groups = groups.filter((item) => item !== group);
  res.status(200).send('Successfully deleted');
});

app.get(
  '/groups/:groupId/users',
  authenticate,
  (req: Request, res: Response) => {
    const groupId = req.params.groupId;
    const group = groups.find((g) => g.getId() === groupId);
    if (!group) {
      res.status(404).send('Group not found');
    }
    res.json(group.getUsers());
  },
);

app.get('/users/:username', authenticate, (req, res) => {
  const username = req.params.username;
  const user = datasource.users.find((u) => u.name === username);

  if (!user) {
    return res.status(404).send('User not found');
  }

  res.json({ id: user.id, name: user.name });
});

app.post('/users', async (req, res) => {
  const { name, email, password } = req.body;

  if (!name || !email || !password) {
    return res.status(400).send('Missing fields');
  }

  const existingUser = datasource.users.find((u) => u.email === email);

  if (existingUser) {
    return res.status(409).send('User already exists');
  }

  const user = new User({ name, email, password });

  await datasource.addUser(user);

  res.status(201).json({ id: user.id, name: user.name });
});

app.post('/login', async (req, res) => {
  const { email, password } = req.body;

  if (!email) {
    return res.status(400).send('Missing email or password');
  }

  const user = datasource.users.find((u) => u.email === email);

  const validPassword = await user?.validatePassword(password);

  if (!validPassword) {
    return res.status(401).send('Unauthorized');
  }

  res.json({ token: user.validToken() });
});
