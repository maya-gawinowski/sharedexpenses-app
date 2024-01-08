/* eslint-disable prettier/prettier */
import { Debt, Expense } from './model';
import { User, IUserForAPI } from './user';

export class Group {
  private readonly id: string;
  private users: IUserForAPI[];
  private debts: Debt[] = [];
  private expenses: Expense[] = [];
  private name: string = '';
  private description: string = '';
  private balance: number = 0;
  private readonly currency: string;

  private static nextId: number = 0;

  constructor(
    users: User[],
    name: string,
    currency: string,
    description?: string,
  ) {
    this.users = users.map((user) => user.forAPI);
    this.id = Group.nextId.toString();
    Group.nextId += 1;
    this.name = name;
    this.currency = currency;
    if (description != 'undefined') {
      this.description = description;
    }
  }

  public addExpense(expense: Expense) {
    this.expenses.push(expense);
    this.balance += expense.amount;
    const participantsIds = expense.participantsIds;
    const payerId = expense.payerId;
    const transferAmount = expense.amount / participantsIds.length;
    participantsIds
      .filter((participantId) => participantId !== payerId)
      .forEach((participantId) => {
        this.modifyDebt(participantId, expense.payerId, transferAmount);
      });
  }

  public getExpenses() {
    return this.expenses;
  }

  public getUsers() {
    return this.users;
  }
  public addUsers(newUsers: User[]) {
    const newUsersForApi = newUsers.map((user) => user.forAPI)
    this.users = this.users.concat(newUsersForApi);
    return newUsers;
  }
  public getDebts() {
    return this.debts;
  }

  public getId() {
    return this.id;
  }
  public getName() {
    return this.name;
  }

  public getCurrency() {
    return this.currency;
  }

  public getBalance() {
    return this.balance;
  }
  public changeName(name: string) {
    this.name = name;
    return this.name;
  }
  public getDescription() {
    return this.description;
  }
  public changeDescription(description: string) {
    this.description = description;
    return this.description;
  }
  private modifyDebt(debtorId: string, creditorId: string, amount: number) {
    return (
      this.tryExtendDebt(debtorId, creditorId, amount) ||
      this.tryReduceDebt(debtorId, creditorId, amount) ||
      this.createDebt(debtorId, creditorId, amount)
    );
  }

  private tryExtendDebt(debtorId: string, creditorId: string, amount: number) {
    const currentDebt = this.debts.find(
      (debt) => debt.debtorId === debtorId && debt.creditorId === creditorId,
    );
    if (currentDebt != null) {
      currentDebt.amount += amount;
    }
    return currentDebt;
  }

  private tryReduceDebt(debtorId: string, creditorId: string, amount: number) {
    const index = this.debts.findIndex(
      (debt) => debt.debtorId === creditorId && debt.creditorId === debtorId,
    );
    const currentDebt = this.debts[index];
    if (currentDebt != null) {
      this.reduceDebt(currentDebt, amount, index);
    }
    return currentDebt;
  }

  private reduceDebt(currentDebt: Debt, amount: number, index: number) {
    currentDebt.amount -= amount;
    if (currentDebt.amount == 0) {
      this.debts.splice(index, 1);
    } else if (currentDebt.amount < 0) {
      this.swapDebtHolders(currentDebt);
    }
  }

  private swapDebtHolders(currentDebt: Debt) {
    const temp = currentDebt.creditorId;
    currentDebt.creditorId = currentDebt.debtorId;
    currentDebt.debtorId = temp;
    currentDebt.amount *= -1;
  }

  private createDebt(debtorId: string, creditorId: string, amount: number) {
    const newDebt: Debt = {
      debtorId: debtorId,
      creditorId: creditorId,
      amount: amount,
    };
    this.debts.push(newDebt);
    return newDebt;
  }
}
