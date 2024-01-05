import {User, UserInput} from './user';
import {Group} from './group';

export class DataSource {
  private _users: User[] = [];
  private _groups: Group[] = [];

  public static readonly instance = new DataSource();

  private constructor() {
  }

  public async initializeData() {
    const names: string[] = [
      'Elena',
      'Marcus',
      'Sophie',
      'Javier',
      'Aria',
      'Nolan',
      'Layla',
      'Caleb',
      'Zara',
      'Mateo',
    ];
    const promises = names.map((name) => this.addUser(new User(this.getUserData(name))))
    await Promise.all(promises);

    this._groups.push(
      new Group(this._users, 'Group Choufleur', 'EUR', 'Shopping'),
    );
    this._groups.push(new Group(this._users, 'Group Carotte', 'EUR', 'Dining'));
    this._groups.push(
      new Group(this._users, 'Group Meringue', 'EUR', 'Other expenses'),
    );
    this._groups.push(
      new Group([this._users[9]], 'Group Souflé', 'EUR', 'Travel expenses again'),
    );
    const choufleur: Group = this._groups[0];
    choufleur.addExpense({
      payerId: '1',
      participantsIds: choufleur.getUsers().map((user) => user.id),
      amount: 100.0,
      date: new Date(),
      description: 'Tickets to the museum',
    });
    choufleur.addExpense({
      payerId: '1',
      participantsIds: choufleur.getUsers().map((user) => user.id),
      amount: 120.0,
      date: new Date(),
      description: 'For wine',
    });
    choufleur.addExpense({
      payerId: '2',
      participantsIds: choufleur.getUsers().map((user) => user.id),
      amount: 230.0,
      date: new Date(),
      description: 'Train tickets',
    });
  }

  private getUserData(name: string): UserInput {
    return {
      name: name,
      email: `${name}@example.com`,
      password: 'password',
    };
  }

  public get users() {
    return this._users;
  }

  public async addUser(user: User) {
    await user.hashPassword();
    this._users.push(user);
  }

  public get groups() {
    return this._groups;
  }
}
