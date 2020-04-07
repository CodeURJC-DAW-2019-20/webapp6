import { Component, OnInit } from '@angular/core';
import { BlogService } from './blog.service';
import { Blog } from './blog.model';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../auth/login.service';
import { Product } from '../product/product.model';
import { ProductService } from '../product/product.service';
import { log } from 'util';

@Component ( {
    selector: 'app-blog',
    templateUrl: './singleBlog.component.html',
    styleUrls: ['./blog.component.css']
} )

export class SingleBlogComponent implements OnInit {

    pService: ProductService;

    blog: Blog;
    //blogs: Blog[] = []; blogs barra derecha

    constructor(private router: Router, activatedRoute: ActivatedRoute, private service: BlogService, public loginService: LoginService) {
        const id = activatedRoute.snapshot.params.id;

        service.getBlogById(id).subscribe((blog => this.blog = blog),(error) => console.error(error));

        //blogs barra derecha
        //service.getAllBlogs().subscribe(
        //    (blogs => this.blogs = blogs),
        //    (error) => console.error(error));

        //console.log(this.blogs.length.toString())
        console.log(loginService.isAdmin)
    }

    deleteBlog() {
        this.service.deleteBlog(this.blog).subscribe((_) => this.router.navigate(['/blog']), (error) => console.error(error));
    }

    ngOnInit(): void {
        //blogs barra derecha
        /*this.service.getAllBlogs().subscribe(
            (blogs => this.blogs = blogs),
            (error) => console.error(error));
            console.log(this.blogs.length.toString())*/
    }

}