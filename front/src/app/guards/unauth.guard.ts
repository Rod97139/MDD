import {CanActivate, Router} from "@angular/router";
import {Injectable} from "@angular/core";
import {SessionService} from "../services/session.service";

@Injectable({providedIn: 'root'})
export class UnauthGuard implements CanActivate {

  constructor(
    private router: Router,
    private sessionService: SessionService,
  ) {
  }

  public canActivate(): boolean {
    if (this.sessionService.isLogged) {
      this.router.navigate(['home']);
      return false;
    }
    return true;
  }
}
