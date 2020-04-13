import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService, User } from '../auth/login.service';
import { Metric } from '../order/metric.model';
import { OrderService } from '../order/order.service';

@Component({
  selector: 'app-userpage',
  templateUrl: './userPage.component.html',
  styleUrls: ['./login.component.css']
})
export class UserPageComponent /*implements OnInit*/ {

  user: User;
  metrics: Metric[];
  average: number;
  lastMetric: Metric;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: LoginService, oService: OrderService) {
    const id = activatedRoute.snapshot.params.id;
    oService.getAllMetrics().subscribe(
      (metrics => {
        this.metrics = metrics,
        this.lastMetric = this.metrics[this.metrics.length - 1],
        this.average = this.lastMetric.average,
        this.lastMetric.averageToPrint = this.lastMetric.average.toFixed(2),
        this.metrics.shift();

      }),
      error => console.log(error),
    )

    oService.getMetrics
    
  }

  ngOnInit(): void {
    this.service.getUserById(this.service.user.id).subscribe(
      user => this.user = user,
      error => console.log(error)
    );
  }

}
