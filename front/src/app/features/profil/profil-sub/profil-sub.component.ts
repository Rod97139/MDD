import {Component, Input} from '@angular/core';
import {Topic} from "../../topic/interfaces/topic.interface";
import {TopicComponent} from "../../topic/components/topic/topic.component";

@Component({
  selector: 'app-profil-sub',
  standalone: true,
  imports: [
    TopicComponent
  ],
  templateUrl: './profil-sub.component.html',
  styleUrl: './profil-sub.component.scss'
})
export class ProfilSubComponent {

  @Input() public sub!: Topic[];
}
