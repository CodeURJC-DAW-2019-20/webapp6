import { Injectable } from '@angular/core';
import {Product} from './product.model';
import { LoginService } from '../auth/login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {catchError, map } from 'rxjs/operators';

const URL = 'api/products/';
const URL2 = 'api/products';
const ALL = 'api/products/all';

@Injectable()
export class ProductService {

  constructor(private loginService: LoginService, private http: HttpClient) { }


  getProducts(): Observable<Product[]> {
    return this.http.get<any>(URL, {withCredentials: true})
      .pipe(
        map(result => result.content),
        catchError((error) => this.handleError(error)));
  }

  getAllProducts(): Observable<Product[]>{
    console.log("pidiendo todos los datos");
    return this.http.get<Product[]>(URL,{withCredentials:true})
        .pipe(catchError((error)=>this.handleError(error)));
  }

  getProductById(id: number| string): Observable<Product> {
    return this.http.get<Product>(URL + id, {withCredentials: true})
    .pipe(catchError((error) => this.handleError(error)));
  }

  getProductsbyPage(page: number): Observable<Product[]> {
    return this.http.get<any>(URL + '?page=' + page, {withCredentials: true})
        .pipe(
            map(result => result.content),
            catchError((error) => this.handleError(error)));
  }

  getProductsbySort(toDo: string, sort: string): Observable<Product[]> {
    return this.http.get<any>(URL2 + '?toDo=' + toDo + '&sort=' + sort, {withCredentials: true})
    .pipe(
      map(result => result.content),
      catchError((error) => this.handleError(error)));
  }

  getProductsbyKey(toDo: string, key: string): Observable<Product[]> {
    return this.http.get<any>(URL2 + '?toDo=' + toDo + '&key=' + key, {withCredentials: true})
    .pipe(
      map(result => result.content),
      catchError((error) => this.handleError(error)));
  }

  getProductsbyFilter(franchise: string, distributor: string, height: number, width: number, min_price: number, max_price: number): Observable<Product[]> {
    return this.http.get<any>(URL2 + '?toDo=filter' + '&franchise=' + franchise + '&distributor=' + distributor +
                              '&width=' + width + '&height=' + height + '&min_price=' + min_price + '&max_price=' +
                               max_price, {withCredentials: true})
    .pipe(catchError((error) => this.handleError(error)));
  }
  
  saveProduct(product: Product): Observable<Product> {
    const body = JSON.stringify(product);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(product);

    if (!product.id) {
        return this.http.post<Product>(URL, body, {headers}).pipe(catchError((error) => this.handleError(error)));
    } else {
        return this.http.put<Product>(URL + product.id, body, {headers}).pipe(catchError((error) => this.handleError(error)));
    }  
  }

  deleteProduct(product: Product): Observable<Product[]> {
    return this.http.delete<Product[]>(URL + product.id).pipe(catchError((error) => this.handleError(error)));
  }

  private handleError(error: any) {
    console.error(error);
    // tslint:disable-next-line: deprecation
    return Observable.throw('Server error (' + error.status + ' ): ' + error);
  }

}
