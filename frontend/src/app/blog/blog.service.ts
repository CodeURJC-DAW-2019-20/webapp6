import { Injectable } from '@angular/core';
import {Blog} from './blog.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';


const URL = 'api/blogs/';


@Injectable({
  providedIn: 'root'
})
export class BlogService {

  constructor(private http: HttpClient) { }

  getBlogs(): Observable<Blog[]> {
    return this.http.get<any>(URL, {withCredentials: true})
      .pipe(
        map(result => result.content),
        catchError((error) => this.handleError(error)));
  }

  getAllBlogs(): Observable<Blog[]>{
    console.log("pidiendo todos los datos");
    return this.http.get<Blog[]>(URL,{withCredentials:true})
        .pipe(catchError((error)=>this.handleError(error)));
  }

  getBlogById(id: number| string): Observable<Blog> {
    return this.http.get<Blog>(URL + id, {withCredentials: true})
    .pipe(catchError((error) => this.handleError(error)));
  }

  getBlogsbyPage(page: number): Observable<Blog[]> {
    return this.http.get<any>(URL + '?page=' + page, {withCredentials: true})
        .pipe(
            map(result => result.content),
            catchError((error) => this.handleError(error)));
  }

  saveBlog(blog: Blog): Observable<Blog> {
    const body = JSON.stringify(blog);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(blog);

    if (!blog.id) {
        return this.http.post<Blog>(URL, body, {headers}).pipe(catchError((error) => this.handleError(error)));
    } else {
        return this.http.put<Blog>(URL + blog.id, body, {headers}).pipe(catchError((error) => this.handleError(error)));
    }  
  }

  deleteBlog(blog: Blog): Observable<Blog[]> {
    return this.http.delete<Blog[]>(URL + blog.id).pipe(catchError((error) => this.handleError(error)));
  }

  private handleError(error: any) {
    console.error(error);
    // tslint:disable-next-line: deprecation
    return Observable.throw('Server error (' + error.status + ' ): ' + error);
  }
}
