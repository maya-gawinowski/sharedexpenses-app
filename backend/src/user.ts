export class User {
  private readonly id: string;
  private name: string;

  private static nextId: number = 0;

  constructor(name: string) {
    this.name = name;
    this.id = User.nextId.toString();
    User.nextId += 1;
  }

  public getId() {
    return this.id;
  }

  public getName() {
    return this.name;
  }

}
