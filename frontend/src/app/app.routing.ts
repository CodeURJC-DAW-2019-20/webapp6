import {Routes, RouterModule} from '@angular/router';
import { BlogComponent } from './blog/blog.component';
import { OrderComponent } from './order/order.component';
import { ProductComponent } from './product/product.component';
import { AppComponent } from './app.component';

const appRoutes : Routes = [
    // Rutas usadas para categorias
  {path:'', component:AppComponent},
  {path:'blog', component:BlogComponent},
  {path:'order', component:OrderComponent},
  {path:'product', component:ProductComponent},
  // ruta usada por defecto a la hora de abrir la pagina
  {path: '', redirectTo: '/', pathMatch: 'full'}
];

 export const routing = RouterModule.forRoot(appRoutes);
