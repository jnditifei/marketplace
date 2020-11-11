import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { configURLApi } from '../Utils/config';
import { User } from '../Models/user';

@Injectable({
    providedIn: 'root'
  })

  export class SellerService {

    constructor(private http: HttpClient) {}

    create(user: User) {
    this.http.post<User>(configURLApi.createSeller, user)
    }

    getById(id: number) {
        return this.http.get<User>(configURLApi.getSellerById(id))
    }

    getAll() {
        return this.http.get<User[]>(configURLApi.getAllSellers)
    }

    update(user: User) {
        this.http.put<User>(configURLApi.updateSeller, user)
    }

    deleteById(id: number) {
        return this.http.delete<User>(configURLApi.deleteSellerById(id))
    }
  }