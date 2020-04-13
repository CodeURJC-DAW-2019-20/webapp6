import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { Order } from '../order/order.model';
import { Address } from './address.model';
import { Observable } from 'rxjs';
import { ITS_JUST_ANGULAR } from '@angular/core/src/r3_symbols';

const URL = '/api';
const URLuser = '/api/user/';

export interface User {
  id?: number;
  name: string;
  delivery: Address;
  orderList: Order[];
  roles: string[];
  passwordHash: string;
}

@Injectable()
export class LoginService {

  isLogged = false;
  isAdmin = false;
  user: User;
  auth: string;

  constructor(private http: HttpClient) {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    if (user) {
      console.log('Logged user');
      this.setCurrentUser(user);
    }
  }

  login(user: string, pass: string) {

    let auth = window.btoa(user + ':' + pass);

    const headers = new HttpHeaders({
      Authorization: 'Basic ' + auth,
      'X-Requested-With': 'XMLHttpRequest',
    });

    return this.http.get<User>('/api/logIn', { headers })
        // tslint:disable-next-line: no-shadowed-variable
        .pipe(map(user => {

          if (user) {
            this.setCurrentUser(user);
            user.passwordHash = auth;
            localStorage.setItem('currentUser', JSON.stringify(user));
          }

          return user;
        }));
  }

  logout() {

    return this.http.get(URL + '/logOut').pipe(
        map(response => {
          this.removeCurrentUser();
          return response;
        }),
    );
  }

  saveUser(user: User): Observable<User>{
    this.setCurrentUser(user);
    const address = user.delivery;
    const body = JSON.stringify(address);
    const id = user.id;
    const shippingnameaux = user.delivery.shippingname;
    //const newpass = user.passwordHash;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    console.log(user);
    return this.http
        .put<User>(URLuser + id + "?shippingName=" + shippingnameaux /*+ "&passwordHash=" + newpass*/, body, {headers})
        .pipe(catchError((error) => this.handleError(error)));
  }

  newUser(user: User): Observable<User>{
    const body = JSON.stringify(user);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http
        .post<User>(URLuser, body, {headers})
        .pipe(catchError((error) => this.handleError(error)));
  }

  private setCurrentUser(user: User) {
    this.isLogged = true;
    this.user = user;
    this.isAdmin = this.user.roles.indexOf('ROLE_ADMIN') !== -1;
  }

  getCurrentUser(id: number| string): Observable<User> {
    return this.http.get<User>(URL + '/user/' + id);
  }

  getCurrentUserId(): number {
    return this.user.id;
  }

  getUserById(id: number| string): Observable<User> {
    return this.http.get<User>(URLuser + id, {withCredentials: true})
    .pipe(catchError((error) => this.handleError(error)));
  }

  removeCurrentUser() {
    localStorage.removeItem('currentUser');
    this.isLogged = false;
    this.isAdmin = false;
  }

  private handleError(error: any) {
    console.error(error);
    // tslint:disable-next-line: deprecation
    return Observable.throw('Server error (' + error.status + ' ): ' + error);
  }

}
