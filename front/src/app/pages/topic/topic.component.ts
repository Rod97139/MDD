import {Component, OnDestroy, OnInit} from "@angular/core";
import {TopicService} from "../../features/topic/services/topic.service";
import {Topic} from "../../features/topic/interfaces/topic.interface";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-topic-page',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent implements OnInit, OnDestroy {


  topics: Topic[] = [];
  subscribedTopics: Topic[] = [];
  $topics!: Subscription;
  $subscribedTopics!: Subscription;

  constructor(
    private topicService: TopicService,
  ) {
  }

  ngOnInit(): void {
    this.loadTopics();
    this.loadSubscribedTopics();
  }

  loadTopics(): void {
    this.$topics = this.topicService.getTopics().subscribe(
      (data) => {
        this.topics = data;
      },
      (error) => {
        console.error('Error fetching topics', error);
      }
    );
  }

  loadSubscribedTopics(): void {
    this.$subscribedTopics = this.topicService.getSubTopics().subscribe(
      (data) => {
        this.subscribedTopics = data;
      },
      (error) => {
        console.error('Error fetching subscribed topics', error);
      }
    );
  }

  ngOnDestroy(): void {
    this.$topics.unsubscribe();
    this.$subscribedTopics.unsubscribe();
  }

}
