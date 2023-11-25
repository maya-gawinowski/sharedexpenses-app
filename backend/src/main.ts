/* eslint-disable prettier/prettier */
import express, { Request, Response } from 'express';
import { PORT } from './config';

import morgan from 'morgan';
import { Group } from './group';
import { Expense } from './model';
import { Datasource } from './datasource';
import { User } from './user';

const app = express();
app.use(morgan('combined'));
app.use(express.json());
app.listen(PORT, () => {
  console.log('Server listening on port:', PORT);
});

const datasource = new Datasource();
let groups = datasource.getGroups();
const users = datasource.getUsers();

app.get('/status', (req: Request, res: Response) => {
  const status = {
    Status: 'Running',
  };
  res.send(status);
});

app.get('/groups/:groupId/debts', (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
  }
  res.json(group.getDebts());
});

app.get('/groups/:groupId/expenses', (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
  }
  res.json(group.getExpenses());
});

app.post('/groups/:groupId/expenses', (req: Request, res: Response) => {
  const expense: Expense = req.body;
  const groupId = req.params.groupId;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
  }
  group.addExpense(expense);
  res.json(group.getExpenses());
});

app.get('/groups', (req: Request, res: Response) => {
  res.json(groups);
});

app.post('/groups', (req: Request, res: Response) => {
  // create Group
  const groupData = req.body;
  if (!groupData.userIds || !groupData.name) {
    res.status(404).send('Users or group name missing');
    return;
  }
  const newUsers: User[] = [];
  for (const uid of groupData.userIds) {
    const user = users.find((u) => u.getId() == uid);
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

app.put('/groups/:groupId', (req: Request, res: Response) => {
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
      if (group.getUsers().find((existing) => existing.getId() == uid)) {
        console.log(`User Id ${uid} already exist in the group`);
        continue;
      }
      const user = users.find((u) => u.getId() == uid);
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
      const userIndex = users.findIndex((u) => u.getId() == uid);
      if (!userIndex) {
        res.status(404).send(`User Id ${uid} not found`);
        return;
      }
      users.splice(userIndex, 1);
    }
  }
  res.status(200).json(group.getUsers());
});

app.delete('/groups/:groupId', (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
    return;
  }
  groups = groups.filter((item) => item != group);
  res.status(200).send('Sucessfully deleted');
});

app.get('/groups/:groupId/users', (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
  }
  res.json(group.getUsers());
});
app.get('/users/:username', (req, res) => {
    const username = req.params.username;
    const user = datasource.getUsers().find(u => u.getName() === username);

    if (!user) {
        return res.status(404).send('User not found');
    }

    res.json({ id: user.getId(), name: user.getName() });
});
