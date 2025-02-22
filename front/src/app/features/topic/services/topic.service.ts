import {Injectable} from "@angular/core";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private pathService = `${environment.baseUrl}topic`;

  constructor(private httpClient: HttpClient) {}

  public getTopics(): Observable<any> {
    return this.httpClient.get(`${this.pathService}`);
  }

  public getSubTopics(): Observable<any> {
    return this.httpClient.get(`${this.pathService}/sub`);
  }
}
