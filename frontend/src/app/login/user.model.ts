import { Order } from '../order/order.model';
import { Address } from '../auth/address.model';

export interface NewUser {
    id?: number;
    name: string;
    passwordHash: string;
    delivery: Address;
    orderList: Order[];
    roles: string[];
}