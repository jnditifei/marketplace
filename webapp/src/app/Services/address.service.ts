import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Address } from '../Models/address';
import { configURLApi } from '../Utils/config';

@Injectable({
    providedIn: 'root'
  })
  export class AddressService {

    constructor(private http: HttpClient) {}

    create(address: Address, userId: number) {
        this.http.post<Address>(configURLApi.createAddress(userId), address)
    }

    update(address: Address) {
        this.http.put<Address>(configURLApi.updateAddress, address)
    }

    getById(id: number) {
        return this.http.get<Address>(configURLApi.getAddressById(id))
    }

    deleteById(id:number) {
        this.http.delete<Address>(configURLApi.deleteBuyerById(id))
    }
  }