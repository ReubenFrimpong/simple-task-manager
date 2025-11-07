import { TaskStatus } from "../enums/task-status";

export interface TaskPayload {
  userId: number;
  title: string;
  description: string;
  status: TaskStatus;
}
