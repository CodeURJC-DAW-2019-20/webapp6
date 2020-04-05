import {Routes, RouterModule} from '@angular/router';
import { BlogComponent } from './blog/blog.component';
import { OrderComponent } from './order/order.component';
import { ProductComponent } from './product/product.component';
import { AppComponent } from './app.component';
import { SingleProductComponent } from './product/singleProduct.component';
import { RegisterComponent } from './login/register.component';
import { LoginComponent } from './login/login.component';
import { UserPageComponent } from './login/userPage.component';

const appRoutes: Routes = [
    // Rutas usadas para categorias
  {path: '', component: AppComponent},
  {path: 'blog', component: BlogComponent},
  {path: 'order', component: OrderComponent},
  {path: 'product', component: ProductComponent},
  {path: 'singleProduct/:id', component: SingleProductComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'userPage/:id', component: UserPageComponent},
  // ruta usada por defecto a la hora de abrir la pagina
  {path: '', redirectTo: '/', pathMatch: 'full'}
];

export const routing = RouterModule.forRoot(appRoutes);
