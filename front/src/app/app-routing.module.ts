import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {AuthGuard} from "./guards/auth.guard";
import {UnauthGuard} from "./guards/unauth.guard";
import {TopicComponent} from "./pages/topic/topic.component";
import {CreatePostComponent} from "./pages/create-post/create-post.component";
import {PostLayoutComponent} from "./pages/post-layout/post-layout.component";
import {ProfilComponent} from "./pages/profil/profil.component";
import {PostDetailsComponent} from "./pages/post-details/post-details.component";

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
    component: ProfilComponent
  },
  {
    path: 'topic',
    canActivate: [AuthGuard],
    component: TopicComponent
  },
  {
    path: 'post',
    canActivate: [AuthGuard],
    component: HomeComponent
  },
  {
    path: 'post-details/:id',
    canActivate: [AuthGuard],
    component: PostDetailsComponent
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
