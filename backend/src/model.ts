/* eslint-disable prettier/prettier */
// eslint-disable-next-line prettier/prettier
export interface User {
  id: string;
  name: string;
}

export interface Debt {
  debtorId: string; // borrowed money, now owes money
  creditorId: string; // gave money, now is owed money
  amount: number;
}

export interface Expense {
  payerId: string;
  participantsIds: string[];
  amount: number;
  description?: string;
  date: Date;
}
