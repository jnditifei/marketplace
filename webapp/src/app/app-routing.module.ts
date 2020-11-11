import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from 'src/app/home/home.component';
import { AllOrderComponent } from 'src/app/order/all-order/all-order.component';
import { OrdersByBuyerComponent } from 'src/app/order/orders-by-buyer/orders-by-buyer.component';
import { NewOrderComponent } from './order/new-order/new-order.component';
import { NewReviewComponent } from './order/new-review/new-review.component';
import { OrderByIdComponent } from './order/order-by-id/order-by-id.component';
import { OrdersBySellerComponent } from './order/orders-by-seller/orders-by-seller.component';


const routes: Routes = [
  { path: '', pathMatch: 'full' , component: HomeComponent},
  { path: 'commandes', component: AllOrderComponent},
  { path: "commande/:id", component: OrderByIdComponent},
  { path: 'commandes-acheteurs', component: OrdersByBuyerComponent},
  { path: 'commandes-vendeurs', component: OrdersBySellerComponent},
  { path: 'commandez', component: NewOrderComponent},
  { path: 'commentaire', component: NewReviewComponent},
  { path: 'orders-by-seller/:id', component: OrdersBySellerComponent},
  { path: 'orders-by-buyer:id', component: OrdersByBuyerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
