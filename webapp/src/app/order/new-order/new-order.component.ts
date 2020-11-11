import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/Models/order';
import { OrderService } from 'src/app/Services/order.service';

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.scss']
})
export class NewOrderComponent implements OnInit {

  order: Order = new Order();

  constructor(private orderService: OrderService,
    private router: Router) { }

  ngOnInit() {
    this.order = new Order;
  }

  save() {
    this.orderService.create(this.order)
    this.order = new Order();
    this.router.navigate(['/commandes'])
  }

  myCustomTrackBy(index, item) { return index; }
}
