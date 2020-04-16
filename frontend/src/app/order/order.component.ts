import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { Router } from '@angular/router';
import { OrderService } from './order.service';
import { LoginService, User } from '../auth/login.service';
import { Product } from '../product/product.model';
import { ProductAmount } from '../product/productamount.model';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  order: Order;
  userAux: User;
  orderAux: Order;

  constructor(private router: Router, private service: OrderService, public loginService: LoginService) { 
    this.order = {status: '', productList: [], total: 0, day: 0, month: 0, year: 0}
  }

  ngOnInit() {
    this.loginService.getUserById(this.loginService.user.id).subscribe(
      user => {this.userAux = user;
          if(this.userAux.orderList[0].id > this.userAux.orderList[1].id && this.userAux.orderList.length == 2){
            /*this.orderAux = this.userAux.orderList[1]
            this.userAux.orderList[1] = this.userAux.orderList[0]
            this.userAux.orderList[0] = this.orderAux
            this.order = this.userAux.orderList[this.userAux.orderList.length-2];*/
            this.loginService.saveUser(this.userAux).subscribe(
              user => this.userAux = user
              ,error => console.log(error));
          }
      }, error => console.log(error)
    );
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

  reloadAmount(id:number,productAux: Product, qt: number){
    const productAmount = { id:id,product: productAux, amount: qt, order: this.order};
    this.service.editOrder(productAmount, this.order.id ).subscribe(
      order => { this.order = order;
                 this.router.navigate(['/order']);
      }, error => console.log(error));
  }

}