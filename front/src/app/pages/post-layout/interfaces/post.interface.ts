import {User} from "../../../interfaces/user.interface";
import {Topic} from "../../../features/topic/interfaces/topic.interface";

export interface Post {
  id: number,
  title: string,
  content: string,
  topic: Topic,
  author: User,
  createdAt: Date,
  updatedAt: Date
}
