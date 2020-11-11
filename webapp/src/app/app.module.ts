import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { AllOrderComponent } from './order/all-order/all-order.component';
import { OrdersByBuyerComponent } from './order/orders-by-buyer/orders-by-buyer.component';
import { NewOrderComponent } from './order/new-order/new-order.component';
import { OrderByIdComponent } from './order/order-by-id/order-by-id.component';
import { NewReviewComponent } from './order/new-review/new-review.component';
import { OrdersBySellerComponent } from './order/orders-by-seller/orders-by-seller.component';
import { HomeComponent } from './home/home.component';
import { UpdateProductComponent } from './product/update-product/update-product.component';
import { CreateProductComponent } from './product/create-product/create-product.component';
import { ProductByIdComponent } from './product/product-by-id/product-by-id.component';
import { AllProductsComponent } from './product/all-products/all-products.component';
import { AllProductsByBrandComponent } from './product/all-products-by-brand/all-products-by-brand.component';
import { ProductsRangeByPriceComponent } from './product/products-range-by-price/products-range-by-price.component';
import { CreateBuyerComponent } from './user/create-buyer/create-buyer.component';
import { CreateSellerComponent } from './user/create-seller/create-seller.component';
import { UpdateSellerComponent } from './user/update-seller/update-seller.component';
import { UpdateBuyerComponent } from './user/update-buyer/update-buyer.component';
import { GetBuyerComponent } from './user/get-buyer/get-buyer.component';
import { GetSellerComponent } from './user/get-seller/get-seller.component';
import { CreateAddressComponent } from './user/create-address/create-address.component';
import { UpdateAddressComponent } from './user/update-address/update-address.component';
import { GetAddressComponent } from './user/get-address/get-address.component';

@NgModule({
  declarations: [
    AppComponent,
    AllOrderComponent,
    OrdersByBuyerComponent,
    OrdersBySellerComponent,
    NewOrderComponent,
    OrderByIdComponent,
    NewReviewComponent,
    HomeComponent,
    UpdateProductComponent,
    CreateProductComponent,
    ProductByIdComponent,
    AllProductsComponent,
    AllProductsByBrandComponent,
    ProductsRangeByPriceComponent,
    CreateBuyerComponent,
    CreateSellerComponent,
    UpdateSellerComponent,
    UpdateBuyerComponent,
    GetBuyerComponent,
    GetSellerComponent,
    CreateAddressComponent,
    UpdateAddressComponent,
    GetAddressComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
