import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NumberGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const id = route.paramMap.get('id');
    if (id && !isNaN(Number(id))) {
      return true;
    } else {
      this.router.navigate(['/404']);
      return false;
    }
  }
}
