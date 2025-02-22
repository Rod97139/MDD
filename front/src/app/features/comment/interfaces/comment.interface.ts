import {UserComment} from "./user-comment.interface";

export interface Comment {
  id: number,
  content: string,
  postId: number,
  user: UserComment,
  createdAt: Date,
  updatedAt: Date
}
