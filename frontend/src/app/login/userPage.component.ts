import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService, User } from '../auth/login.service';

@Component({
  selector: 'app-userpage',
  templateUrl: './userPage.component.html',
  styleUrls: ['./login.component.css']
})
export class UserPageComponent /*implements OnInit*/ {

  user: User;

  constructor(private router: Router, activatedRoute: ActivatedRoute, private service: LoginService) {
    const id = activatedRoute.snapshot.params.id;
    console.log("id, " + id);
    service.getUserById(id).subscribe((user => this.user = service.user), (error) => console.error(error));
    //this.user = service.user;
    console.log(service.user)
  }

  //ngOnInit(): void {
  //}

}
