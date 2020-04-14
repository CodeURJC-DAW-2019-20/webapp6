import { Component } from '@angular/core';
import { ProductService} from './product.service';
import { Product} from './product.model';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../auth/login.service';
import { OrderService } from '../order/order.service';
import { Order } from '../order/order.model';
import { ProductAmount} from './productamount.model';

@Component({
  selector: 'app-product',
  templateUrl: './singleProduct.component.html',
  styleUrls: ['./product.component.css']
})

export class SingleProductComponent {

  product: Product;
  pAmount: ProductAmount;
  order: Order;
  qty: number;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ProductService, public loginService: LoginService, private orderService: OrderService) {
    const id = activatedRoute.snapshot.params.id;
    service.getProductById(id).subscribe((product => this.product = product), (error) => console.error(error));
  }

  ngOnInit() {
    this.qty = 0;
    this.orderService.getCurrentOrder().subscribe(
      order => this.order = order,
      error => console.log(error)
    );
  }

  deleteProduct() {
    this.service.deleteProduct(this.product).subscribe((_) => this.router.navigate(['/product']), (error) => console.error(error));
  }

  addProductToOrder(productAux: Product, qt: number){
    let productAmount = { product: productAux, amount: qt }
    this.pAmount = productAmount;
    this.orderService.addProduct(this.pAmount, this.order.id ).subscribe(
      order => { this.order = order;
                  this.router.navigate(['/order']);
      },error => console.log(error));
  }

}
