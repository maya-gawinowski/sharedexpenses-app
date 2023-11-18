import {User} from "./user";
import {Group} from "./group";

export class Datasource {
  private users: User[] = [];
  private groups: Group[] = [];

  constructor() {
    this.initializeData();
  }

  private initializeData() {
    const names: string[] = [
      "Elena",
      "Marcus",
      "Sophie",
      "Javier",
      "Aria",
      "Nolan",
      "Layla",
      "Caleb",
      "Zara",
      "Mateo"
    ]
    names.forEach( name => this.users.push(new User(name)))

    this.groups.push(new Group(
      this.users,
      "Group Choufleur",
      "EUR",
      "Shopping")
    );
    this.groups.push(new Group(
      this.users,
      "Group Carotte",
      "EUR",
      "Dining")
    );
    this.groups.push(new Group(
      this.users,
      "Group Meringue",
      "EUR",
      "Other expenses")
    );
    this.groups.push(new Group(
      this.users,
      "Group Souflé",
      "EUR",
      "Travel expenses again")
    );
    const choufleur: Group = this.groups[0]
    choufleur.addExpense({
      payerId: "1",
      participantsIds: choufleur.getUsers().map(user => user.getId()),
      amount: 100.0,
      date: new Date(),
      description: "Tickets to the museum"
    })

  }

  public getUsers() {
    return this.users;
  }

  public getGroups() {
    return this.groups;
  }
}