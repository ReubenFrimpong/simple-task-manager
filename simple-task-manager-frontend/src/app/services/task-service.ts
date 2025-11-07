import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { TaskDetails } from '../interfaces/task-details';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  http = inject(HttpClient);

  getTasksByUserId(userId: number) {
    return this.http.get('http://localhost:8080/api/tasks', { params: { userId } });
  }

  createTask(task: any) {
    return this.http.post('http://localhost:8080/api/tasks', task);
  }

  updateTask(task: TaskDetails) : Observable<TaskDetails> {
    return this.http.put<TaskDetails>(`http://localhost:8080/api/tasks/${task.id}`, task);
  }

  deleteTask(taskId: number) {
    return this.http.delete(`http://localhost:8080/api/tasks/${taskId}`);
  }

}
