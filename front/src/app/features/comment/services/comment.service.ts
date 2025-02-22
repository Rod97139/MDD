import {Injectable} from "@angular/core";
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CommentRequest} from "../interfaces/commentRequest.interface";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private pathService = `${environment.baseUrl}comment`;

  constructor(private httpClient: HttpClient) {}

  public getComments(postId: number): Observable<any> {
    return this.httpClient.get(`${this.pathService}/${postId}`);
  }

  public addComment(comment: CommentRequest): Observable<any> {
    return this.httpClient.post(`${this.pathService}`, comment);
  }
}
