import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { Router } from '@angular/router';
import { OrderService } from './order.service';
import { LoginService, User } from '../auth/login.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html'
})
export class ConfirmationComponent implements OnInit {

  order: Order;
  user: User;

  constructor(private router: Router, private orderService: OrderService, public loginService: LoginService) { }  

  ngOnInit() {
    this.orderService.getCurrentOrder().subscribe(
      order => this.order = order,
      error => console.log(error)
    );
    this.loginService.getCurrentUser(this.loginService.user.id).subscribe(
        user => this.user = user,
        error => console.log(error)
    );
  }

}