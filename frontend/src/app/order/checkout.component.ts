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
  dirs: Address[] = [];

  constructor(private router: Router, private orderService: OrderService, public loginService: LoginService) {
    this.order = {status: '', productList: [], total: 0, day: 0, month: 0, year: 0}
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

  finish(shippingnameaux: string, lastnameaux:string, companyaux:string,numberaux:string,emailaux:string,streetaux:string,flooraux:string,cityaux:string,countryaux:string,zipcodeaux:string){
    //Meter los datos dentro de la dirección del user
      let auxaddress = {shippingname: shippingnameaux,
                        lastname: lastnameaux,
                        company: companyaux,
                        number: numberaux,
                        email: emailaux,
                        street: streetaux,
                        floor: flooraux,
                        city: cityaux,
                        country: countryaux,
                        zipcode: zipcodeaux
      }
      let auxaddress2 = {shippingname: shippingnameaux,
        lastname: lastnameaux,
        company: companyaux,
        number: numberaux,
        email: emailaux,
        street: streetaux,
        floor: flooraux,
        city: cityaux,
        country: countryaux,
        zipcode: zipcodeaux
}
      this.dirs.push(auxaddress)
      this.dirs.push(auxaddress2)
      this.orderService.finishOrder(this.dirs, this.order.id).subscribe(
        order => this.order = order,
        error => console.log(error)
      );
  }  

}