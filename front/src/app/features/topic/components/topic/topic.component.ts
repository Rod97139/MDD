import {Component, Input} from '@angular/core';
import {Topic} from "../../interfaces/topic.interface";

@Component({
  selector: 'app-topic',
  standalone: true,
  imports: [],
  templateUrl: './topic.component.html',
  styleUrl: './topic.component.scss'
})
export class TopicComponent {
  @Input() topic!: Topic;

}
