import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService, User } from '../auth/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, public service: LoginService, ) { }

  user: User;

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
  }

}
