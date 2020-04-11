import { Injectable } from '@angular/core';
import { LoginService, User } from '../auth/login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Order } from './order.model';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { ProductAmount } from '../product/productamount.model';
import { Address } from '../auth/address.model';
import { Metric } from './metric.model';

const URL = '/api/order/';
const URLmetrics = '/api/metrics/';

@Injectable({ providedIn: 'root' })
export class OrderService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

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

  getMetrics(): Observable<Metric> {
    return this.http.get<Metric>(URLmetrics, {withCredentials: true})
    .pipe(catchError((error) => this.handleError(error)));
  }

  getAllMetrics(): Observable<Metric[]> {
    return this.http.get<Metric[]>(URLmetrics + "all", {withCredentials: true})
    .pipe(catchError((error) => this.handleError(error)));
  }

  /*updateMetrics(metric: Metric): Observable<Metric> {
    const body = JSON.stringify(metric);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<Metric>(URL, body, {headers}).pipe(catchError((error) => this.handleError(error)));
  }*/

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
    return this.http.get<Order>(URL + '0', this.httpOptions)
    .pipe(catchError((error) => this.handleError(error)));
  }

  addProduct(pAmount: ProductAmount, id: number): Observable<Order>{
    const body = JSON.stringify(pAmount);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
  });
    return this.http.post<Order>(URL + id, body, {headers}).pipe(catchError((error) => this.handleError(error))) as Observable<Order>;
  }

  finishOrder(address: Address[], id:number): Observable<Order>{
    const body = JSON.stringify(address);
    const headers = new HttpHeaders({
      'Content-Type':  'application/json'
    })
    return this.http.put<any>(URL + id + "/confirmation", body, {headers}).pipe(catchError((error) => this.handleError(error))) as Observable<Order>;
  }

  deleteProductFromOrder(orderId : number, pId: number): Observable<Order> {
    return this.http.delete<any>(URL + orderId + "/" + pId).pipe(catchError((error) => this.handleError(error))) as Observable<Order>;
  }

}