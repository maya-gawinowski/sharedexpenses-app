import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';
import { JWT_SECRET } from './config';

export class User {
  private readonly _id: string;
  private _name: string;
  private _email: string;
  private _password: string;

  private static nextId: number = 0;

  constructor(data: UserInput) {
    this._email = data.email;
    this._name = data.name;
    this._password = data.password;

    this._id = this.getNextId(data.id);
  }

  private getNextId(id?: string) {
    if (id !== undefined) {
      console.log('id is not undefined');
      return id;
    }
    return (User.nextId++).toString();
  }

  public get id() {
    return this._id;
  }

  public get name() {
    return this._name;
  }

  public get email() {
    return this._email;
  }

  public get password() {
    return this._password;
  }

  public async hashPassword() {
    this._password = await bcrypt.hash(this._password, 10);
  }

  public validatePassword(password: string) {
    return bcrypt.compare(password, this._password);
  }

  public validToken() {
    return jwt.sign({ id: this._id }, JWT_SECRET, { expiresIn: '24h' });
  }
}

export interface IUser {
  id: string;
  name: string;
  email: string;
  password: string;
}

export type UserInput = Omit<IUser, 'id'> & { id?: string };
