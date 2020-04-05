import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {Blog} from './blog.model';
import { BlogService } from './blog.service';


@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  blog: Blog[] = [];

  constructor(private router: Router, activatedRoute: ActivatedRoute, private service: BlogService) {
    const id = activatedRoute.snapshot.params.id;
    service.getAllBlogs().subscribe((blogs => this.blog = blogs), (error) => console.error(error));
   }

  ngOnInit(): void {
  }

}
