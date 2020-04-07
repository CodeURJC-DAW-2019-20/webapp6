import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService, User } from '../auth/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./login.component.css']
})
export class RegisterComponent {

  user: User;

  constructor(private router: Router, private service: LoginService) {

  }




/**  savUser(user: User): Observable<User> {
 *      const formData = new FormData();
 *      formData.append('name', user.name);
 *      formData.append('email', user.delivery.email);
 *     formData.append('pass', user.pass);
 *   return this.http.post<User>('https://localhost:8443/api/user/', formData);
 * }
 */

saveUser() {
  this.service.saveUser(this.user).subscribe(
  _ => {}, (error: Error) => console.error('error creating new user: ' + error));
  // window.history.back();
}

}
