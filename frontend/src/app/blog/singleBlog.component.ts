import { Component, OnInit } from '@angular/core';
import { BlogService } from './blog.service';
import { Blog } from './blog.model';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../auth/login.service';
import { ProductService } from '../product/product.service';

@Component ( {
    selector: 'app-blog',
    templateUrl: './singleBlog.component.html',
    styleUrls: ['./blog.component.css']
} )

export class SingleBlogComponent {

    blog: Blog;
    //blogs: Blog[] = []; blogs barra derecha

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: BlogService, public loginService: LoginService) {
        const id = activatedRoute.snapshot.params.id;
        service.getBlogById(id).subscribe((blog => this.blog = blog),(error) => console.error(error));
        console.log(loginService.user.name)

        //blogs barra derecha
        //service.getAllBlogs().subscribe(
        //    (blogs => this.blogs = blogs),
        //    (error) => console.error(error));

        //console.log(this.blogs.length.toString())
    }

    deleteBlog() {
        this.service.deleteBlog(this.blog).subscribe((_) => this.router.navigate(['/blog']), (error) => console.error(error));
    }

    //ngOnInit(): void {
        //blogs barra derecha
        /*this.service.getAllBlogs().subscribe(
            (blogs => this.blogs = blogs),
            (error) => console.error(error));
            console.log(this.blogs.length.toString())*/
    //}

}