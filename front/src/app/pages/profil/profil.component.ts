import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProfilSetupComponent} from "../../features/profil/profil-setup/profil-setup.component";
import {Topic} from "../../features/topic/interfaces/topic.interface";
import {TopicService} from "../../features/topic/services/topic.service";
import {Subscription} from "rxjs";
import {ProfilSubComponent} from "../../features/profil/profil-sub/profil-sub.component";

@Component({
  selector: 'app-profil',
  standalone: true,
  imports: [
    ProfilSetupComponent,
    ProfilSubComponent
  ],
  templateUrl: './profil.component.html',
  styleUrl: './profil.component.scss'
})
export class ProfilComponent implements OnInit, OnDestroy {

  public sub!: Topic[];
  $sub!: Subscription;

  constructor(
    private topicService: TopicService
  ) {
  }

  loadSub(): void {
    this.$sub = this.topicService.getSubTopics().subscribe((topics: Topic[]) => {
      this.sub = topics;
    });
  }

  ngOnInit(): void {
    this.loadSub();
  }

  ngOnDestroy(): void {
    this.$sub.unsubscribe();
  }

}
