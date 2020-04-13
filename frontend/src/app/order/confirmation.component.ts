import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { Router } from '@angular/router';
import { OrderService } from './order.service';
import { LoginService, User } from '../auth/login.service';
//import { Metric } from './metric.model';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html'
})
export class ConfirmationComponent implements OnInit {

  order: Order;
  user: User;
  tax: number = 2.90;
  totalaux: number;
  /*metric: Metric = { average: null, averageToPrint: 0, 
    lastMoney:0, toPrint: 0, totalMoney: 0, totalOrders: 0};*/

  constructor(private router: Router, private orderService: OrderService, public loginService: LoginService) { 
    this.order = {status: '', productList: [], total: 0, day: 0, month: 0, year: 0}
    this.totalaux = 0;
    this.loginService.getUserById(this.loginService.user.id).subscribe(
      user => this.user = user,
      error => console.log(error)
    );
  }  

  ngOnInit() {
    this.loginService.getUserById(this.loginService.user.id).subscribe(
      user => this.user = user,
      error => console.log(error)
  );
    this.orderService.getCurrentOrder().subscribe(
      order => {this.order = order;
                this.totalaux = order.total + this.tax},
      error => console.log(error),
    );
    /*this.orderService.getMetrics().subscribe(
      metric => {
        console.log(metric),
        this.metric = metric,
        console.log(this.metric),
        this.metric.lastMoney = this.order.total,
        this.metric.totalOrders = this.metric.totalOrders + 1;
        this.metric.totalMoney = this.metric.totalMoney + this.order.total,
        this.metric.average = this.metric.totalMoney / this.metric.totalOrders,
        this.metric.averageToPrint = Number(this.metric.average).toFixed(2),

        this.orderService.updateMetrics(this.metric).subscribe(),
        console.log(this.metric)
      
      },
      error => console.log(error)
    );*/

  }

}