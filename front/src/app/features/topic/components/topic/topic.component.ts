import {Component, Input} from '@angular/core';
import {Topic} from "../../interfaces/topic.interface";
import {TopicService} from "../../services/topic.service";

@Component({
  selector: 'app-topic',
  standalone: true,
  imports: [],
  templateUrl: './topic.component.html',
  styleUrl: './topic.component.scss'
})
export class TopicComponent {
  @Input() topic!: Topic;
  @Input() isSubscribed!: boolean;

  constructor(private topicService: TopicService) {}

  toggleSubscription() {
    if (this.isSubscribed) {
      this.unSubscribeToTopic();
    } else {
      this.subscribeToTopic();
    }
  }

  subscribeToTopic() {
    this.topicService.subscribe(this.topic.id).subscribe(
      response => {
        console.log('Subscribed successfully', response);
        this.isSubscribed = true;
      },
      error => {
        console.error('Subscription failed', error);
      }
    );
  }

  unSubscribeToTopic() {
    this.topicService.unsubscribe(this.topic.id).subscribe(
      response => {
        console.log('Unsubscribed successfully', response);
        this.isSubscribed = false;
      },
      error => {
        console.error('Unsubscription failed', error);
      }
    );
  }
}
