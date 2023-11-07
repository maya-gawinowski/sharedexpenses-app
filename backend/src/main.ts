/* eslint-disable prettier/prettier */
import express, { Request, Response } from 'express';
import { PORT } from './config';

import morgan from 'morgan';
import { Group } from './group';
import { Expense, User } from './model';

const app = express();
app.use(morgan('combined'));
app.use(express.json());
app.listen(PORT, () => {
  console.log('Server listening on port:', PORT);
});

const Luca: User = { id: '1', name: 'Luca' };
const Maya: User = { id: '2', name: 'Maya' };
const Mitsch: User = { id: '3', name: 'Mitsch' };
const Manca: User = { id: '4', name: 'Manca' };
const users: User[] = [Luca, Maya, Mitsch, Manca];
const group: Group = new Group(
  [Maya, Mitsch],
  'Group 1',
  'This is a test group',
);

let groups: Group[] = [group];

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
    const user = users.find((u) => u.id == uid);
    if (!user) {
      res.status(404).send(`User Id ${uid} not found`);
      return;
    }
    newUsers.push(user);
  }
  const group = new Group(newUsers, groupData.name, groupData.description);
  groups.push(group);
  res.status(201).json(group);
});
app.put('/groups/:groupId', (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const userIds = req.body.userIds;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
    return;
  }
  const newUsers: User[] = [];

  for (const uid of userIds) {
    if (group.getUsers().find((existing) => existing.id == uid)) {
      console.log(`User Id ${uid} already exist in the group`);
      continue;
    }
    const user = users.find((u) => u.id == uid);
    if (!user) {
      res.status(404).send(`User Id ${uid} not found`);
      return;
    }
    newUsers.push(user);
  }
  group.addUsers(newUsers);
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
