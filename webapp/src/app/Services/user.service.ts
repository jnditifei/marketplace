import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { configURLApi } from '../Utils/config';
import { User } from '../Models/user';

@Injectable({
    providedIn: 'root'
  })
  export class UserService {

    constructor(private http: HttpClient) {}

    create (user: User) {
        return this.http.get<User>(configURLApi.login)
    }
  }