import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService, User } from '../auth/login.service';

@Component({
  selector: 'app-useredit',
  templateUrl: './userEdit.component.html',
  styleUrls: ['./login.component.css']
})
export class UserEditComponent /*implements OnInit*/ {

  user: User;

  constructor(private router: Router, activatedRoute: ActivatedRoute, private service: LoginService) {
    const id = activatedRoute.snapshot.params.id;
    console.log("id, " + id);
    this.service.getUserById(this.service.user.id).subscribe(
      user => this.user = user,
      error => console.log(error)
    );
    //this.user = service.user;
    console.log(service.user)
  }

  ngOnInit(): void {
    if(!this.service.isLogged)
          this.router.navigate(['/login/'])
    this.service.getUserById(this.service.user.id).subscribe(
      user => this.user = user,
      error => console.log(error)
    );
  }

  saveInfo(){
      this.service.saveUser(this.user).subscribe(
        user => { this.user = user;
                  this.router.navigate(['/userPage/' + this.service.getCurrentUserId()]);
        },error => console.log(error));
  }

}