import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { Router } from '@angular/router';
import { OrderService } from './order.service';
import { LoginService, User } from '../auth/login.service';
import { Address } from '../auth/address.model';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html'
})
export class CheckoutComponent implements OnInit {

  order: Order;
  user: User;

  constructor(private router: Router, private orderService: OrderService, public loginService: LoginService) { }

  changeAdress(shippingname: string, lastname:string, company:string,number:string,email:string,street:string,floor:string,city:string,country:string,zipcode:string){
    //Meter los datos dentro de la dirección del user
        
       /* this.user.delivery.shippingname = shippingname;
        this.user.delivery.lastname = lastname;
        this.user.delivery.company = company;
        this.user.delivery.number = number;
        this.user.delivery.email = email;
        this.user.delivery.street = street;
        this.user.delivery.floor = floor;
        this.user.delivery.city = city;
        this.user.delivery.country = country;
        this.user.delivery.zipcode = zipcode;
        this.loginService.saveUser(this.user);*/
  }  

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