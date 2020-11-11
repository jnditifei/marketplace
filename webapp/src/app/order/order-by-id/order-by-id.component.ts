import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/Models/order';
import { OrderService } from 'src/app/Services/order.service';

@Component({
  selector: 'app-order-by-id',
  templateUrl: './order-by-id.component.html',
  styleUrls: ['./order-by-id.component.scss']
})
export class OrderByIdComponent implements OnInit {

  id: string;
  order: Order;

  constructor(private service: OrderService, private route: ActivatedRoute, private router: Router) { 
  
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];

    this.service.getById(this.id).subscribe(data => {this.order = data;})
  }

  deleteById(id: number) {
    this.service.deleteById(id)
  }
  navigateTo(route: string) : void{
    this.router.navigate([route]);
  } 

}
