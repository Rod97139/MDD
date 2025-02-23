import {Component, OnDestroy, OnInit} from '@angular/core';
import {PostService} from "./services/post.service";
import {PostComponent} from "./post/post.component";
import {Post} from "./interfaces/post.interface";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-post-layout',
  standalone: true,
  imports: [
    PostComponent
  ],
  templateUrl: './post-layout.component.html',
  styleUrl: './post-layout.component.scss'
})
export class PostLayoutComponent implements OnInit, OnDestroy {

  posts: Post[] = [];
  private $post!: Subscription;

  constructor(
    private postService: PostService
    ) {
  }

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts(): void {
    this.$post = this.postService.getPosts().subscribe(
      (data) => {
        this.posts = data;
      },
      (error) => {
        console.error('Error fetching posts', error);
      }
    );
  }

  ngOnDestroy(): void {
    this.$post.unsubscribe();
  }
}
