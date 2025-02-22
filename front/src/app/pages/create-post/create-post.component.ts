import {Component, OnInit} from '@angular/core';
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatCard, MatCardContent, MatCardHeader, MatCardTitle} from "@angular/material/card";
import {MatFormField, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {NgForOf, NgIf} from "@angular/common";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {TopicService} from "../../features/topic/services/topic.service";
import {Topic} from "../../features/topic/interfaces/topic.interface";
import {PostService} from "../post-layout/services/post.service";
import {MatOption, MatSelect} from "@angular/material/select";

@Component({
  selector: 'app-create-post',
  standalone: true,
  imports: [
    MatButton,
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardTitle,
    MatFormField,
    MatIcon,
    MatIconButton,
    MatInput,
    MatSuffix,
    NgIf,
    ReactiveFormsModule,
    MatSelect,
    MatOption,
    NgForOf
  ],
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.scss'
})
export class CreatePostComponent implements OnInit {

  public topics: Topic[] = [];

  public form = this.fb.group({
    topic: ['', [Validators.required]],
    title: ['', [Validators.required]],
    content: ['', [Validators.required]]
  });

  constructor(
              private topicService: TopicService,
              private postService: PostService,
              private fb: FormBuilder,
              private router: Router,
  ) { }

  ngOnInit(): void {
    this.loadTopics();
  }

  public loadTopics(): void {
    this.topicService.getTopics().subscribe(topics => {
      this.topics = topics;
    });
  }

  public onSubmit(): void {
    if(this.form.valid) {
      this.postService.createPost(this.form.value).subscribe(
        () => {
          this.router.navigate(['/home']);
        },
        error => {
          console.error('Error creating post', error);
        }
      );
    }
  }
}
