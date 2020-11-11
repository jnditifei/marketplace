import { Address } from './address';

export class User{

    userID: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    birthdate: Date;
    sex: string;
    addresses: Address[];
    phoneNumber: string;
    role: string;

}