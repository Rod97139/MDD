import {Component, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {AuthService} from "./features/auth/services/auth.service";
import {Router} from "@angular/router";
import {SessionService} from "./services/session.service";
import {Observable, Subscription} from "rxjs";
import {User} from "./interfaces/user.interface";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  $me!: Subscription;
  constructor(
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService,
    private renderer: Renderer2
  ) {}

  public ngOnInit(): void {
    this.autoLog();
    this.renderer.listen('document', 'click', (event: Event) => {
      const target = event.target as HTMLElement;
      if (!target.closest('.header-links') && !target.closest('.menu-hamburger')) {
        document.querySelector('.header-links')?.classList.remove('open');
      }
    });
  }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  public autoLog(): void {
    this.$me = this.authService.me().subscribe(
      (user: User) => {
        this.sessionService.logIn(user);
      },
      (_) => {
        this.sessionService.logOut();
      }
    )
  }

  toggleMenu() {
    document.querySelector('.header-links')?.classList.toggle('open');
  }

  public ngOnDestroy(): void {
    this.$me.unsubscribe();
  }
}
