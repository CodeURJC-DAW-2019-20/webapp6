import { User } from '../auth/login.service';
import { ProductAmount } from '../product/productamount.model';

export interface Order {

  id?: number;
  status: string;
  productList?: ProductAmount[];
  user?: User;
  total: number;
  day: number;
  month: number;
  year: number;
}
