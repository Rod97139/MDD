import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PostService} from "./services/post.service";

@Component({
  selector: 'app-post-layout',
  standalone: true,
  imports: [],
  templateUrl: './post-layout.component.html',
  styleUrl: './post-layout.component.scss'
})
export class PostLayoutComponent implements OnInit {

  posts: any[] = [];

  constructor(
    private router: Router,
    private postService: PostService
    ) {
  }

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts(): void {
    this.postService.getPosts().subscribe(
      (data) => {
        this.posts = data;
      },
      (error) => {
        console.error('Error fetching posts', error);
      }
    );
  }

  createPost() {
    this.router.navigate(['/create-post']);
  }
}
