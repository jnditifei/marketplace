import { User } from './user';

export class Address {

    adressId: number;
    streetName: string;
    postalCode: string;
    city: string;
    country: string;
    owner: User;

}