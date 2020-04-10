import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {MatMenuModule} from '@angular/material/menu';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatBadgeModule} from '@angular/material/badge';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatRadioModule} from '@angular/material/radio';
import {MatSliderModule} from '@angular/material/slider';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';

import { AppComponent } from './app.component';
import { BlogComponent } from './blog/blog.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProductComponent } from './product/product.component';
import { OrderComponent } from './order/order.component';
import { routing } from './app.routing';
import { SingleProductComponent } from './product/singleProduct.component';
import { ProductService } from './product/product.service';
import { LoginService } from './auth/login.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './login/register.component';
import { BasicAuthInterceptor } from './auth/auth.interceptor';
import { ErrorInterceptor } from './auth/error.interceptor';
import { FormsModule} from '@angular/forms';
import { UserPageComponent } from './login/userPage.component';
import { OrderService } from './order/order.service';
import { CheckoutComponent } from './order/checkout.component';
import { ConfirmationComponent } from './order/confirmation.component';
import { SingleBlogComponent } from './blog/singleBlog.component'
import {MatTabsModule} from '@angular/material/tabs';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    BlogComponent,
    HeaderComponent,
    FooterComponent,
    ProductComponent,
    SingleProductComponent,
    SingleBlogComponent,
    OrderComponent,
    LoginComponent,
    RegisterComponent,
    UserPageComponent,
    CheckoutComponent,
    ConfirmationComponent,
    HomeComponent
  ],
  imports: [
    NgbModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatToolbarModule,
    MatBadgeModule,
    MatButtonModule,
    MatIconModule,
    MatRadioModule,
    MatSliderModule,
    routing,
    MatTabsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDialogModule,
    routing
  ],
  providers: [ProductService, LoginService,OrderService,
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent, ProductComponent],
  exports: [ProductComponent]
})
export class AppModule { }
