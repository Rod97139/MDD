import { Component } from "@angular/core";
import {Router} from "@angular/router";
import {TopicService} from "../../features/topic/services/topic.service";
import {Topic} from "../../features/topic/interfaces/topic.interface";

@Component({
  selector: 'app-topic-page',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent {


  topics: Topic[] = [];

  constructor(
    private router: Router,
    private topicService: TopicService,
  ) {
  }

  ngOnInit(): void {
    this.loadTopics();
  }

  loadTopics(): void {
    this.topicService.getTopics().subscribe(
      (data) => {
        this.topics = data;
      },
      (error) => {
        console.error('Error fetching topics', error);
      }
    );
  }

}
