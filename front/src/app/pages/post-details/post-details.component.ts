import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../post-layout/services/post.service";
import {Post} from "../post-layout/interfaces/post.interface";
import {NgForOf} from "@angular/common";
import {CommentService} from "../../features/comment/services/comment.service";
import {Comment} from "../../features/comment/interfaces/comment.interface";
import {Subscription, take} from "rxjs";

@Component({
  selector: 'app-post-details',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.scss'
})
export class PostDetailsComponent implements OnInit, OnDestroy {
  postId!: string;
  post!: Post;
  comments: Comment[] = [];
  $comments!: Subscription;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService
  ) {}

  ngOnInit(): void {
    this.postId = this.route.snapshot.paramMap.get('id')!;
    this.postService.getPostById(Number(this.postId)).pipe(take(1)).subscribe((post: any) => {
      this.post = post;
    });
    this.$comments = this.commentService.getComments(Number(this.postId)).subscribe((comments: any) => {
      this.comments = comments;
      console.log(this.comments);
    });
  }

  ngOnDestroy(): void {
    this.$comments.unsubscribe();
  }
}
