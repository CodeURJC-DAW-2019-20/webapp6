import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Blog } from './blog.model';
import { BlogService } from './blog.service';
import { LoginService } from '../auth/login.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from '../product/product.model';
import { ProductService } from '../product/product.service';


@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  blog: Blog[] = [];
  newblog: Blog;
  date: Date;
  products: Product[] = [];
  newProduct: Product;

  constructor(private router: Router, activatedRoute: ActivatedRoute, private service: BlogService, private pservice: ProductService, public loginService: LoginService, private modalService: NgbModal) {
    const id = activatedRoute.snapshot.params.id;
    this.newProduct = { name:'', franchise:'', distributor:'', price:0, description:'', 
                        height:0, width:0, weight:0, reference:'', stock:0, image: '../assets/img/product/notavailable.png', imagefull: '../assets/img/product/notavailable2.png' }
    this.newblog = {
      author: "", name: "", text: "", image: "../assets/img/blog/newblog.png",
      textfull: "", day: null, month: null, year: null, idproduct: 0, product: this.newProduct
    };

  }

  ngOnInit(): void {
    this.service.getAllBlogs().subscribe(
      (blogs => this.blog = blogs),
      (error) => console.error(error));

      this.pservice.getAllProducts().subscribe(
        products => this.products = products,
        error => console.log(error)
      );

  }

  delete(blog: Blog) {
    this.service.deleteBlog(blog).subscribe(
      blog => this.blog = blog,
      error => console.log(error)
    );
  }

  search(key: string) {
    this.service.getBlogsByKey(key).subscribe(
      blog => this.blog = blog,
      error => console.log(error)
    );
  }

  saveBlog() {
    this.service.saveBlog(this.newblog).subscribe(
    _ => {this.router.navigate(['/blog']);}, (error: Error) => console.error('error creating new blog entrie: ' + error));
  }

  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true, size: 'lg' });
  }

}

