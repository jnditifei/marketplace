import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/Models/order';
import { OrderService } from 'src/app/Services/order.service';

@Component({
  selector: 'app-orders-by-seller',
  templateUrl: './orders-by-seller.component.html',
  styleUrls: ['./orders-by-seller.component.scss']
})
export class OrdersBySellerComponent implements OnInit {

  orders: Order[];
  id: string;

  constructor(
    private router: Router, 
    private orderService: OrderService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.orderService.getBySeller(this.id).subscribe(data => {this.orders =data})
  }

  navigateTo(route: string) : void{
    this.router.navigate([route]);
  } 

}
