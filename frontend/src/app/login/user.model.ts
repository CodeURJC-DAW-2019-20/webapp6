import { Order } from '../order/order.model';
import { Address } from '../auth/address.model';

export interface User {
    id?: number;
    name: string;
    pass: string;
    delivery: Address;
    orderList: Order[];
    roles: string[];
}