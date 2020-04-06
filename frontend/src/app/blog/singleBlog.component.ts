import { Component } from '@angular/core';
import { BlogService } from './blog.service';
import { Blog } from './blog.model';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../auth/login.service';
import { Product } from '../product/product.model';
import { ProductService } from '../product/product.service';
//  import { RelatedProductComponent } from './relatedProduct.component';

@Component ( {
    templateUrl: './singleBlog.component.html',
    styleUrls: ['./blog.component.css']
} )

export class SingleBlogComponent {

    pService: ProductService;

    blog: Blog;
    product: Product;
    //relatedProduct: RelatedProductComponent;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: BlogService, public loginService: LoginService, pService: ProductService) {
        const id = activatedRoute.snapshot.params.id;
        service.getBlogById(id).subscribe((blog => this.blog = blog), (error) => console.error(error));
        //pService.getProductById(this.blog.idproduct).subscribe((product => this.product = product), (error) => console.error(error));
        //this.relatedProduct.getRelatedProduct(this.blog.idproduct);

    }

    deleteBlog() {
        this.service.deleteBlog(this.blog).subscribe((_) => this.router.navigate(['/blog']), (error) => console.error(error));
    }

}