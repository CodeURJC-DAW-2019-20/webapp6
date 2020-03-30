import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BlogComponent } from './blog/blog.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProductComponent } from './product/product.component';
import { OrderComponent } from './order/order.component';
import { routing } from './app.routing';

@NgModule({
  declarations: [
    AppComponent,
    BlogComponent,
    HeaderComponent,
    FooterComponent,
    ProductComponent,
    OrderComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
