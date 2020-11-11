import { Review } from './review';

export class Order {

    orderId: number;
    price: number;
    orderDate: Date;
    shipmentDate: Date;
    status: string;
    buyerId: number;
    sellerId: number;
    addressId: number;
    productId: string[];
    review: Review;

}
