import { Component, OnInit } from '@angular/core';
import { Product } from '../product/product.model';
import { ProductService } from '../product/product.service';
import { BlogService } from '../blog/blog.service';
import { Blog } from '../blog/blog.model';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
  })
  
  export class HomeComponent implements OnInit{

    products: Product[];
    blogs: Blog[];

    constructor(private productService: ProductService,private blogService: BlogService) {
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
    }
  }