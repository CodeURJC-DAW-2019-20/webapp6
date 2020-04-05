import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { Router } from '@angular/router';
import { OrderService } from './order.service';
import { LoginService } from '../auth/login.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  order: Order;

  constructor(private router: Router, private service: OrderService, public loginService: LoginService) { }

  ngOnInit() {
    this.service.getCurrentOrder().subscribe(
      order => this.order = order,
      error => console.log(error)
    );
  }

}