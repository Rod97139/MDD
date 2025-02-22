import {Component, Input} from '@angular/core';
import {Post} from "../interfaces/post.interface";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post',
  standalone: true,
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss'
})
export class PostComponent {
  @Input() post!: Post;

  constructor(private router: Router) {}

  navigateToDetails() {
    this.router.navigate(['/post-details', this.post.id]);
  }
}
