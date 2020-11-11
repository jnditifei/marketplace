import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '../Models/order';
import { configURLApi } from '../Utils/config';
import { User } from '../Models/user';

@Injectable({
    providedIn: 'root'
  })
  export class BuyerService {

    constructor(private http: HttpClient) {}

    create(user: User) {
        this.http.post<User>(configURLApi.createBuyer, user)
    }

    getById(id: number) {
        return this.http.get<User>(configURLApi.getBuyerById(id))
    }

    getAll(){
        return this.http.get<User[]>(configURLApi.getAllBuyers)
    }

    update(user: User) {
        this.http.put<User>(configURLApi.updateBuyer, user)
    }

    deleteById(id: number) {
        this.http.delete<User>(configURLApi.deleteBuyerById(id))
    }

  }