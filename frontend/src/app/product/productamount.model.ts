import { Order } from '../order/order.model';
import { Product } from './product.model';

export interface ProductAmount {

  id?: number;
  product: Product;
  amount: number;
  total: number;
  order: Order;
}
