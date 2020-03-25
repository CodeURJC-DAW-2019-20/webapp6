import {Routes, RouterModule} from '@angular/router';

const appRoutes = [
    // Rutas usadas para categorias
  {path: '', component: '' /*appComponent*/ , useAsDefault: true },

  // ruta usada por defecto a la hora de abrir la pagina
  {path: '', redirectTo: '/', pathMatch: 'full'}
];

export const routing = RouterModule.forRoot(appRoutes);
