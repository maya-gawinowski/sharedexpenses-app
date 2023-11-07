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

const group: Group = new Group(
  [
    { id: '1', name: 'Luca' } as User,
    { id: '2', name: 'Maya' } as User,
    { id: '3', name: 'Mitsch' } as User,
    { id: '4', name: 'Manca' } as User,
  ],
  'Group 1',
  'This is a test group',
);

let groups = [group];

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
  if (!groupData.users || !groupData.name) {
    res.status(404).send('Users or group name missing');
  }
  const group = new Group(
    groupData.users,
    groupData.name,
    groupData.description,
  );
  groups.push(group);
  res.status(201).json(group);
});
app.put('/groups/:groupId', (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const users: User[] = req.body.users;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
  }
  group.addUsers(users);
  res.status(200).json(group.getUsers());
});
app.delete('/groups/:groupId', (req: Request, res: Response) => {
  const groupId = req.params.groupId;
  const group = groups.find((g) => g.getId() === groupId);
  if (!group) {
    res.status(404).send('Group not found');
  }
  groups = groups.filter((item) => item != group);
  res.status(200).send('Sucessfully deleted');
});
