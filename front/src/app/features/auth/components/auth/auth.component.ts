import {Component} from "@angular/core";
import {MatCard, MatCardContent, MatCardHeader} from "@angular/material/card";
import {MatFormField} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatButton} from "@angular/material/button";
import {Router} from "@angular/router";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  standalone: true,
  imports: [
    MatCardHeader,
    MatCardContent,
    MatCard,
    MatFormField,
    MatIcon,
    MatButton
  ],
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent  {

  constructor(
    private router: Router
  ) { }

  login() {
    this.router.navigate(['/login'])
  }

  register() {
    this.router.navigate(['/register'])

  }
}
