import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/Models/order';
import { OrderService } from 'src/app/Services/order.service';

@Component({
  selector: 'app-orders-by-buyer',
  templateUrl: './orders-by-buyer.component.html',
  styleUrls: ['./orders-by-buyer.component.scss']
})
export class OrdersByBuyerComponent implements OnInit {

  orders: Order[];

  id: string;

  order: Order = new Order;
  constructor(
    private router: Router,
    private orderService: OrderService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.orderService.getByBuyer(this.id).subscribe(data => {this.orders = data})
  }

  navigateTo(route: string) : void{
    this.router.navigate([route]);
  } 

}
