import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { Router } from '@angular/router';
import { OrderService } from './order.service';
import { LoginService, User } from '../auth/login.service';
import { Address } from '../auth/address.model';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  order: Order;
  user: User;
  dirs: Address[] = [];
  tax: number = 2.90;
  totalaux: number;

  constructor(private router: Router, private orderService: OrderService, public loginService: LoginService) {
    this.totalaux = 0;
    this.order = {status: '', productList: [], total: 0, day: 0, month: 0, year: 0}
   }

  ngOnInit() {
    if(!this.loginService.isLogged)
          this.router.navigate(['/login'])
    this.orderService.getCurrentOrder().subscribe(
      order => {this.order = order;
                this.totalaux = order.total + this.tax},
      error => console.log(error)
    );
    this.loginService.getUserById(this.loginService.user.id).subscribe(
        user => this.user = user,
        error => console.log(error)
    );
    //this.totalaux = this.order.total + this.tax;
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