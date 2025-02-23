import {User} from "../../../interfaces/user.interface";

export interface Post {
  id: number,
  title: string,
  content: string,
  topic: number,
  author: User,
  createdAt: Date,
  updatedAt: Date
}
