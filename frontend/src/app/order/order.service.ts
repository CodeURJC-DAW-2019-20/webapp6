import { Injectable } from '@angular/core';
import { LoginService, User } from '../auth/login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Order } from './order.model';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

const URL = '/api/order/';

@Injectable()
export class OrderService {

  constructor(private loginService: LoginService, private http: HttpClient) { }

  getOrders(): Observable<Order[]> {
    return this.http.get<any>(URL, {withCredentials: true})
      .pipe(
        map(result => result.content),
        catchError((error) => this.handleError(error)));
  }
  
  getAllOrders(): Observable<Order[]>{
    console.log("pidiendo todos los datos");
    return this.http.get<Order[]>(URL,{withCredentials:true})
        .pipe(catchError((error)=>this.handleError(error)));
  }

  getOrderById(id: number| string): Observable<Order> {
    return this.http.get<Order>(URL + id, {withCredentials: true})
    .pipe(catchError((error) => this.handleError(error)));
  }

  saveOrder(order: Order): Observable<Order> {
    const body = JSON.stringify(order);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(order);

    if (!order.id) {
        return this.http.post<Order>(URL, body, {headers}).pipe(catchError((error) => this.handleError(error)));
    } else {
        return this.http.put<Order>(URL + order.id, body, {headers}).pipe(catchError((error) => this.handleError(error)));
    }  
  }

  deleteOrder(order : Order): Observable<Order> {
    return this.http.delete<Order>(URL + order.id).pipe(catchError((error) => this.handleError(error)));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + ' ): ' + error);
  }

  getCurrentOrder(): Observable<Order> {  
    return this.http.get<Order>(URL + '0', {withCredentials: true})
    .pipe(catchError((error) => this.handleError(error)));
  }

}