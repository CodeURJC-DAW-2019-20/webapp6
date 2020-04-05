import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from '../order/order.service';
import { LoginService, User } from '../auth/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: User;

  constructor(private router: Router, private order: OrderService, public service: LoginService) { }

  ngOnInit(): void {
  }

  logOut() {
    this.service.logout().subscribe(
        (response) => {
            this.router.navigate(['/']);
        },
        (error) => console.log('Error when trying to log out: ' + error),
    );
  }

}
