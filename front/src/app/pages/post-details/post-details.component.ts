import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../post-layout/services/post.service";
import {Post} from "../post-layout/interfaces/post.interface";
import {DatePipe, NgForOf, NgOptimizedImage} from "@angular/common";
import {CommentService} from "../../features/comment/services/comment.service";
import {Comment} from "../../features/comment/interfaces/comment.interface";
import {lastValueFrom, Subscription, take} from "rxjs";
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {MatFormField, MatInput} from "@angular/material/input";
import {MatCardContent} from "@angular/material/card";

@Component({
  selector: 'app-post-details',
  standalone: true,
  imports: [
    NgForOf,
    FormsModule,
    ReactiveFormsModule,
    MatButton,
    MatInput,
    MatFormField,
    MatCardContent,
    DatePipe,
    NgOptimizedImage
  ],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.scss'
})
export class PostDetailsComponent implements OnInit, OnDestroy {
  postId!: string;
  post!: Post;
  comments: Comment[] = [];
  $comments!: Subscription;
  $addComment!: Subscription;
  public form = this.fb.group({
    content: ['', [Validators.required]],
  });

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService,
    private fb: FormBuilder
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
    if (this.$addComment) {
      this.$addComment.unsubscribe();
    }
  }

  submitComment() {
    if (this.form.valid) {
      this.$addComment = this.commentService.addComment({
        content: this.form.value.content!,
        postId: Number(this.postId)
      }).subscribe((comment: any) => {
        console.log(comment);
        this.comments.push(comment);
        this.form.reset();
      });
    }
  }
}
