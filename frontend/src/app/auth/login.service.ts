import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Order } from '../order/order.model';
import { Address } from './address.model';
import { Observable } from 'rxjs';

const URL = '/api';

export interface User {
  id?: number;
  name: string;
  roles: string[];
  delivery: Address;
  orderList: Order;
  authdata: string;
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
        .pipe(map(user => {

          if (user) {
            this.setCurrentUser(user);
            user.authdata = auth;
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

  saveUser(user: User): Observable<User> {
    const formData = new FormData();
    formData.append('name', user.name);
    formData.append('authdata', user.authdata);
    return this.http.post<User>('https://localhost:8443/api/user/', formData);
  }

  private setCurrentUser(user: User) {
    this.isLogged = true;
    this.user = user;
    this.isAdmin = this.user.roles.indexOf('ROLE_ADMIN') !== -1;
  }

  removeCurrentUser() {
    localStorage.removeItem('currentUser');
    this.isLogged = false;
    this.isAdmin = false;
  }
}
