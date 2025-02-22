import {Component, OnInit} from '@angular/core';
import {User} from "../../../interfaces/user.interface";
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../auth/services/auth.service";
import {UserUpdateRequest} from "../../auth/interfaces/userUpdateRequest.interface";

@Component({
  selector: 'app-profil-setup',
  standalone: true,
  imports: [
    MatButton,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatFormField,
    MatInput,
    ReactiveFormsModule
  ],
  templateUrl: './profil-setup.component.html',
  styleUrl: './profil-setup.component.scss'
})
export class ProfilSetupComponent implements OnInit {

  public me!: User;

  public form = this.fb.group({
    name: ['', [Validators.required, Validators.min(2)]],
    email: ['', [Validators.required, Validators.email]]
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
  ) {}

  loadMe(): void {
    this.authService.me().subscribe((user: User) => {
      this.me = user;
      this.form.patchValue({
        name: user.name,
        email: user.email
      });
    });
  }

  submit(): void {
    this.authService.update(this.form.value as UserUpdateRequest).subscribe(
      (response) => {
        localStorage.setItem('token', response.token);
      },
        error => {
          console.error('Error editing user details', error);
        }
    );
  }

  ngOnInit(): void {
    this.loadMe();
  }

}
