import { Component, OnInit, Inject } from '@angular/core';
import { ProductService} from './product.service';
import { OrderService } from '../order/order.service';
import { Product} from './product.model';
import { ProductAmount} from './productamount.model';
import { Router, NavigationEnd } from '@angular/router';
import { LoginService } from '../auth/login.service';
import { Order } from '../order/order.model';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent implements OnInit {

  product: Product;
  products: Product[];
  franchises: string[] = [];
  distributors: string[] = [];
  value = 100;
  min = 0;
  max = 3000;
  autoTicks = false;
  showTicks = false;
  tickInterval = 1;
  franchise = '';
  distributor = '';
  height = 150;
  width = 150;
  recommendedProducts: Product[];
  pAmount: ProductAmount;
  order: Order;
  closeResult = '';
  newProduct: Product;
  mySubscription: any;

  constructor(private router: Router, private service: ProductService, public loginService: LoginService, private orderService: OrderService, private modalService: NgbModal) {
    this.order = {status: '', productList: [], total: 0, day: 0, month: 0, year: 0};
    this.newProduct = { name: '', franchise: '', distributor: '', price: 0, description: '',
                        height: 0, width: 0, weight: 0, reference: '', stock: 0, image: '../assets/img/product/notavailable.png', imagefull: '../assets/img/product/notavailable2.png' };
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
        return false;
    };
    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Trick the Router into believing it's last link wasn't previously loaded
        this.router.navigated = false;
      }
    });
  }

  ngOnInit() {
    this.service.getAllProducts().subscribe(
      products => this.products = products,
      error => console.log(error)
    );
    this.service.getAllProducts().subscribe(
      products => this.getFranchisesAndDistributors(products),
      error => console.log(error)
    );
    this.service.getProductsbyPage(1).subscribe(
      products => this.getFranchisesAndDistributors(products),
      error => console.log(error)
    );
    this.service.getProductsbyRecommendations().subscribe(
      recommendedProducts => this.recommendedProducts = recommendedProducts,
      error => console.log(error)
    );
    this.orderService.getCurrentOrder().subscribe(
      order => this.order = order,
      error => console.log(error)
    );
  }

  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }

  formatLabel(value: number) {
    if (value >= 0) {
      return Math.round(value / 1) + '€';
    }
    return value;
  }

  getSliderTickInterval(): number | 'auto' {
    if (this.showTicks) {
      return this.autoTicks ? 'auto' : this.tickInterval;
    }
    return 0;
  }

  sortBy(toDo: string, sort: string) {
    this.service.getProductsbySort(toDo, sort).subscribe(
      products => this.products = products,
      error => console.log(error)
    );
  }

  search(key: string) {
    this.service.getProductsbyKey('search', key).subscribe(
      products => this.products = products,
      error => console.log(error)
    );
  }

  delete(product: Product) {
    this.service.deleteProduct(product).subscribe(
      products => this.products = products,
      error => console.log(error)
    );
  }

  saveProduct() {
    this.service.saveProduct(this.newProduct).subscribe(
    _ => {this.router.navigate(['/product']); }, (error: Error) => console.error('error creating new product: ' + error));
  }

  filter() {
    this.service.getProductsbyFilter(this.franchise, this.distributor, this.width, this.height, this.min, this.value).subscribe(
      products => this.products = products,
      error => console.log(error)
    );
  }

  getFranchisesAndDistributors(products: Product[]) {
    const franchisesaux: string[] = [];
    const distributorsaux: string[] = [];
    for (let i = 0; i < products.length; i++) {
      if (!franchisesaux.includes(products[i].franchise) && !this.franchises.includes(products[i].franchise)) {
        franchisesaux.push(products[i].franchise);
      }
      if (!distributorsaux.includes(products[i].distributor) && !this.distributors.includes(products[i].distributor)) {
        distributorsaux.push(products[i].distributor);
      }
    }
    this.franchises = this.franchises.concat(franchisesaux);
    this.distributors = this.distributors.concat(distributorsaux);
  }

  addProductToOrder(productAux: Product, qt: number) {
    const productAmount = { product: productAux, amount: qt };
    this.pAmount = productAmount;
    this.orderService.addProduct(this.pAmount, this.order.id ).subscribe(
      order => { this.order = order;
                 this.router.navigate(['/order']);
      }, error => console.log(error));
  }

  openVerticallyCentered(content) {
    this.modalService.open(content, { centered: true, size: 'lg' });
  }
}

