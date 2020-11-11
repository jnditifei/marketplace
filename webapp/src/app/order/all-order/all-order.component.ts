import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Address } from 'src/app/Models/address';
import { Order } from 'src/app/Models/order';
import { User } from 'src/app/Models/user';
import { AddressService } from 'src/app/Services/address.service';
import { BuyerService } from 'src/app/Services/buyer.service';
import { OrderService } from 'src/app/Services/order.service';
import { SellerService } from 'src/app/Services/seller.service';

@Component({
  selector: 'app-all-order',
  templateUrl: './all-order.component.html',
  styleUrls: ['./all-order.component.scss']
})
export class AllOrderComponent implements OnInit {

  orders: Observable<Order[]>;
  seller: User;
  buyer: User;
  address: Address;

  constructor(
    private service: OrderService, private buyerService: BuyerService, private addressService: AddressService,
    private sellerService: SellerService, private router: Router) { }

  ngOnInit() {
    this.orders = this.service.getAll();
  }

  getSeller(id: number){
    this.sellerService.getById(id).subscribe(data =>{this.seller = data});
    return this.seller.firstName
  }

  getBuyer(id: number){
    this.buyerService.getById(id).subscribe(data => {this.buyer = data});
    return this.buyer.firstName; 
  }

  getAddress(id: number){
    this.addressService.getById(id).subscribe(data => {this.address = data});
    return this.address.adressId;
  }

  navigateTo(route: string) : void{
    this.router.navigate([route]);
  } 

  
}
