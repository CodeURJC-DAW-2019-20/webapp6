import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService, User } from '../auth/login.service';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, public service: LoginService, ) { }

  user: User;
  hide = true;
  pass = new FormControl('', [Validators.required]);
  name = new FormControl('', [Validators.required]);

  logIn(name: string, pass: string) {

    this.service.login(name, pass).subscribe(
        (u) => {
            console.log(u);
            this.router.navigate(['/']);
        },
        (error) => alert('Invalid user or password'),
    );
  }

  logOut() {
    this.service.logout().subscribe(
        (response) => {
            this.router.navigate(['/']);
        },
        (error) => console.log('Error when trying to log out: ' + error),
    );
  }

  ngOnInit(): void {
    if(this.service.isLogged)
          this.router.navigate(['/userPage/' + this.service.getCurrentUserId()])
  }

  getErrorMessage() {
    if (this.pass.hasError('required')) {
      return 'Introduzca un valor';
    }
    if (this.name.hasError('required')) {
      return 'Introduzca un valor';
    }
  }

}
