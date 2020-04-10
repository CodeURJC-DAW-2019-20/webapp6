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

  constructor(private router: Router, private service: OrderService, public loginService: LoginService) { 
    this.order = {status: '', productList: [], total: 0, day: 0, month: 0, year: 0}
  }

  ngOnInit() {
    this.service.getCurrentOrder().subscribe(
      order => this.order = order,
      error => console.log(error)
    );
  }

  delete(id:number){
    this.service.deleteProductFromOrder(this.order.id, id).subscribe(
      order => this.order = order,
      error => console.log(error)
    );
  }

}