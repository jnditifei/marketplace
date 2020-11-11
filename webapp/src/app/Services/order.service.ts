import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '../Models/order';
import { configURLApi } from '../Utils/config';
import { Review } from '../Models/review';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {}

  create(order: Order){
    this.http.post<Order>(configURLApi.createOrder, order)
  }
  
  getById(id: string){
    return this.http.get<Order>(configURLApi.getOrderById(id))
  }

  getByBuyer(id: string){
    return this.http.get<Order[]>(configURLApi.getOrdersByBuyer(id))
  }

  getBySeller(id: string){
    return this.http.get<Order[]>(configURLApi.getOrdersBySeller(id))
  }

  getAll(){
    return this.http.get<Order[]>(configURLApi.getAllOrders)
  }

  deleteById(id: number){
    this.http.delete<Order>(configURLApi.deleteOrderById(id))
  }

  newComment(id: string, review: Review){
    this.http.post(configURLApi.newReview(id), review)
  }
}
