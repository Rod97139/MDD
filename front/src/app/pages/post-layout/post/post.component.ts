import {Component, Input} from '@angular/core';
import {Post} from "../interfaces/post.interface";

@Component({
  selector: 'app-post',
  standalone: true,
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss'
})
export class PostComponent {
  @Input() post!: Post;
}
