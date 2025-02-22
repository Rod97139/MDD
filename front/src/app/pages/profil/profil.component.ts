import {Component, OnInit} from '@angular/core';
import {User} from "../../interfaces/user.interface";
import {AuthService} from "../../features/auth/services/auth.service";
import {ProfilSetupComponent} from "../../features/profil/profil-setup/profil-setup.component";
import {Topic} from "../../features/topic/interfaces/topic.interface";
import {TopicService} from "../../features/topic/services/topic.service";

@Component({
  selector: 'app-profil',
  standalone: true,
  imports: [
    ProfilSetupComponent
  ],
  templateUrl: './profil.component.html',
  styleUrl: './profil.component.scss'
})
export class ProfilComponent implements OnInit {

  public sub!: Topic[];

  constructor(
    private topicService: TopicService
  ) {
  }

  loadSub(): void {
    this.topicService.getSubTopics().subscribe((topics: Topic[]) => {
      this.sub = topics;
    });
  }

  ngOnInit(): void {
    this.loadSub();
  }

}
