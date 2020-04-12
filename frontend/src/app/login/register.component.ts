import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService, User } from '../auth/login.service';
import { Order } from '../order/order.model'
import { Address } from '../auth/address.model';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./login.component.css']
})
export class RegisterComponent {

  user: User;
  address: Address;
  orderList: Order[] = [];

  constructor(private router: Router, private service: LoginService) {

    this.address = { shippingname: '', lastname: '', company: '', number: '', email: '', street: '', floor: '', city: '', country: '', zipcode: '' }
    this.user = { name: '', passwordHash: '', delivery: this.address, orderList: this.orderList, roles: ['ROLE_USER'] }

  }




/**  savUser(user: User): Observable<User> {
 *      const formData = new FormData();
 *      formData.append('name', user.name);
 *      formData.append('email', user.delivery.email);
 *     formData.append('pass', user.pass);
 *   return this.http.post<User>('https://localhost:8443/api/user/', formData);
 * }
 */

newUser() {
  this.service.newUser(this.user).subscribe(
  _ => { this.router.navigate(['/login']);},
   (error: Error) => console.error('error creating new user: ' + error));
  // window.history.back();
}

}
