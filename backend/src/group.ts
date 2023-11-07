/* eslint-disable prettier/prettier */
import { Debt, Expense, User } from './model';

export class Group {
  private readonly id: string;
  private users: User[];
  private debts: Debt[] = [];
  private expenses: Expense[] = [];
  private name: string = '';
  private description: string = '';

  private static nextId: number = 0;

  constructor(users: User[], name: string, description?: string) {
    this.users = users;
    this.id = Group.nextId.toString();
    Group.nextId += 1;
    this.name = name;
    if (description != 'undefined') {
      this.description = description;
    }
  }

  public addExpense(expense: Expense) {
    this.expenses.push(expense);
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
    this.users = this.users.concat(newUsers);
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
