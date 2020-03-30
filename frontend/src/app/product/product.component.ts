import { Component, OnInit } from '@angular/core';
import { ProductService} from './product.service';
import { Product} from './product.model';
import { Router } from '@angular/router';
import { LoginService } from '../auth/login.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[];

  constructor(private router: Router, private service: ProductService, public loginService: LoginService) { }

  ngOnInit() {
    this.service.getProducts().subscribe(
      products => this.products = products,
      error => console.log(error)
    );
  }

}
