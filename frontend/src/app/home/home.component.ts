import { Component, OnInit } from '@angular/core';
import { Product } from '../product/product.model';
import { ProductService } from '../product/product.service';
import { BlogService } from '../blog/blog.service';
import { Blog } from '../blog/blog.model';
import { ProductAmount} from '../product/productamount.model';
import { OrderService } from '../order/order.service';
import { Order } from '../order/order.model';
import { Router } from '@angular/router';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
  })
  
  export class HomeComponent implements OnInit{

    products: Product[];
    blogs: Blog[];
    pAmount: ProductAmount;
    order: Order;

    constructor(private productService: ProductService,private blogService: BlogService, private orderService: OrderService, private router: Router) {
      this.order = {status: '', productList: [], total: 0, day: 0, month: 0, year: 0}
    }
    ngOnInit() {
        this.productService.getAllProducts().subscribe(
          products => this.products = products,
          error => console.log(error)
        );
        this.blogService.getAllBlogs().subscribe(
          blogs => this.blogs = blogs,
          error => console.log(error)
        );
        this.orderService.getCurrentOrder().subscribe(
      order => this.order = order,
      error => console.log(error)
        );
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