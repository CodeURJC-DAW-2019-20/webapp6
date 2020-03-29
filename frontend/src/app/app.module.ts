import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BlogComponent } from './blog/blog.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProductComponent } from './product/product.component';
import { RouterOutlet } from '@angular/router';
import { OrderComponent } from './order/order.component';

@NgModule({
  declarations: [
    AppComponent,
    BlogComponent,
    HeaderComponent,
    FooterComponent,
    ProductComponent,
    OrderComponent,
    RouterOutlet
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
