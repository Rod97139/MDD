import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../post-layout/services/post.service";
import {Post} from "../post-layout/interfaces/post.interface";
import {NgForOf} from "@angular/common";
import {CommentService} from "../../features/comment/services/comment.service";
import {Comment} from "../../features/comment/interfaces/comment.interface";
import {AuthService} from "../../features/auth/services/auth.service";

@Component({
  selector: 'app-post-details',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.scss'
})
export class PostDetailsComponent implements OnInit {
  postId!: string;
  post!: Post;
  comments: Comment[] = [];

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService
  ) {}

  ngOnInit(): void {
    this.postId = this.route.snapshot.paramMap.get('id')!;
    this.postService.getPostById(Number(this.postId)).subscribe((post: any) => {
      this.post = post;
    });
    this.commentService.getComments(Number(this.postId)).subscribe((comments: any) => {
      this.comments = comments;
      console.log(this.comments);
    });
  }
}
