import {Injectable} from "@angular/core";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private pathService = `${environment.baseUrl}post`;

  constructor(private httpClient: HttpClient) {}

  public getPosts(): Observable<any> {
    return this.httpClient.get(`${this.pathService}/sub`);
  }

  public createPost(post: any): Observable<any> {
    return this.httpClient.post(`${this.pathService}`, post);
  }
}
