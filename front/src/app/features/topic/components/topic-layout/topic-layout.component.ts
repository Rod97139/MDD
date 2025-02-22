import {Component, Input} from '@angular/core';
import {Topic} from "../../interfaces/topic.interface";
import {TopicComponent} from "../topic/topic.component";

@Component({
  selector: 'app-topic-layout',
  standalone: true,
  templateUrl: './topic-layout.component.html',
  imports: [
    TopicComponent
  ],
  styleUrl: './topic-layout.component.scss'
})
export class TopicLayoutComponent {
  @Input() topics!: Topic[];
  @Input() subscribedTopics!: Topic[];

  isSubscribed(id: number) {
    return this.subscribedTopics.some(subscribedTopic => subscribedTopic.id === id)
  }
}
