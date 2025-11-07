import { Component, inject } from '@angular/core';
import { TaskService } from '../services/task-service';
import { TaskDetails } from '../interfaces/task-details';
import { TaskPayload } from '../interfaces/task-payload';
import { TaskStatus } from '../enums/task-status';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { TaskEditDialog } from '../task-edit-dialog/task-edit-dialog';
import { TaskDeleteDialog } from '../task-delete-dialog/task-delete-dialog';

@Component({
  selector: 'app-task',
  imports: [
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    MatListModule,
  ],
  templateUrl: './task.html',
  styleUrl: './task.scss'
})
export class Task {
  taskService = inject(TaskService);
  router = inject(Router);
  userId = JSON.parse(localStorage.getItem('user') || '{}').id;
  taskPayload: TaskPayload = {
    userId: this.userId,
    title: '',
    description: '',
    status: TaskStatus.PENDING
  };
  error = { title: '', description: '' };
  tasks: TaskDetails[] = [];
  dialog = inject(MatDialog);


  ngOnInit() {
    this.getTasks();
  }

  public getTasks() {
    this.taskService.getTasksByUserId(this.userId).subscribe({
      next: (response) => {
        this.tasks = response as TaskDetails[];
        this.taskPayload.title = '';
        this.taskPayload.description = '';
      },
      error: (error) => {
        console.error('Failed to fetch tasks:', error);
      }
    });
  }

  public createTask() {
    this.taskService.createTask(this.taskPayload).subscribe({
      next: (response) => {
        this.tasks.push(response as TaskDetails);
        this.taskPayload.title = '';
        this.taskPayload.description = '';
        this.error = { title: '', description: '' };
      },
      error: (error) => {
        if (error.status === 403) {
          localStorage.removeItem('authToken');
          this.router.navigate(['/login']);
          return;
        }
        if (error.status === 400) {
          console.log('Validation errors:', error);
          this.error = error.error;
          return;
        }
      }
    });
  }

  public openEditDialog(task: TaskDetails) {
    const dialogRef = this.dialog.open(TaskEditDialog, {
      data: { ...task }
    });

    dialogRef.afterClosed().subscribe({
      next: () => this.getTasks()
    });
  }

  public openDeleteDialog(taskId: number) {
    const dialogRef = this.dialog.open(TaskDeleteDialog, {
      data: taskId
    });

    dialogRef.afterClosed().subscribe({
      next: () => this.getTasks()
    });
  }
}
