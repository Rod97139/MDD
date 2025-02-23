import {Component, OnDestroy, OnInit} from '@angular/core';
import {PostService} from "./services/post.service";
import {PostComponent} from "./post/post.component";
import {Post} from "./interfaces/post.interface";
import {Subscription} from "rxjs";
import {MatOption, MatSelect, MatSelectChange} from "@angular/material/select";

@Component({
  selector: 'app-post-layout',
  standalone: true,
  imports: [
    PostComponent,
    MatSelect,
    MatOption
  ],
  templateUrl: './post-layout.component.html',
  styleUrl: './post-layout.component.scss'
})
export class PostLayoutComponent implements OnInit, OnDestroy {

  posts: Post[] = [];
  sort: string = 'date';
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

  sortPosts(event: MatSelectChange): void {
    const sortBy = event.value;

    if (sortBy === 'date') {
      this.posts = [...this.posts].sort((a, b) =>
        new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
      );
    } else if (sortBy === 'topic') {
      this.posts = [...this.posts].sort((a, b) =>
        b.topic.id - a.topic.id
      );
    }
  }
}
