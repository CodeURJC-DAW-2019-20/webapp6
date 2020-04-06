import { Component } from '@angular/core';
import { ProductService} from './product.service';
import { Product} from './product.model';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../auth/login.service';

@Component({
  templateUrl: './singleProduct.component.html'
})

export class SingleProductComponent {

  product: Product;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: ProductService, public loginService: LoginService) {
    const id = activatedRoute.snapshot.params.id;
    service.getProductById(id).subscribe((product => this.product = product), (error) => console.error(error));
  }

  deleteProduct() {
    this.service.deleteProduct(this.product).subscribe((_) => this.router.navigate(['/products']), (error) => console.error(error));

  }

}
