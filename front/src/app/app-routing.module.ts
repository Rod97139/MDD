import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {AuthGuard} from "./guards/auth.guard";
import {UnauthGuard} from "./guards/unauth.guard";
import {TopicComponent} from "./pages/topic/topic.component";
import {CreatePostComponent} from "./pages/create-post/create-post.component";

const routes: Routes = [
  {
    path: 'home',
    canActivate: [AuthGuard],
    component: HomeComponent
  },
  {
    path: '',
    canActivate: [UnauthGuard],
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },
  {
    path: 'me',
    canActivate: [AuthGuard],
    component: HomeComponent
  },
  {
    path: 'topic',
    canActivate: [AuthGuard],
    component: TopicComponent
  },
  {
    path: 'create-post',
    canActivate: [AuthGuard],
    component: CreatePostComponent
  },

  // { path: '404', component: NotFoundComponent },
  // { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
