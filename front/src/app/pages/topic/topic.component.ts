import { Component } from "@angular/core";
import {TopicService} from "../../features/topic/services/topic.service";
import {Topic} from "../../features/topic/interfaces/topic.interface";

@Component({
  selector: 'app-topic-page',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent {


  topics: Topic[] = [];
  subscribedTopics: Topic[] = [];

  constructor(
    private topicService: TopicService,
  ) {
  }

  ngOnInit(): void {
    this.loadTopics();
    this.loadSubscribedTopics();
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

  loadSubscribedTopics(): void {
    this.topicService.getSubTopics().subscribe(
      (data) => {
        this.subscribedTopics = data;
      },
      (error) => {
        console.error('Error fetching subscribed topics', error);
      }
    );
  }

}
