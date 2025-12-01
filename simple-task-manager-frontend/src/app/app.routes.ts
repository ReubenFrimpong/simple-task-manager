import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Registration } from './registration/registration';
import { Task } from './task/task';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'register', component: Registration },
  { path: '', component: Task, canActivate: [authGuard] },
  { path: 'dashboard/tasks', component: Task, canActivate: [authGuard] },
];


