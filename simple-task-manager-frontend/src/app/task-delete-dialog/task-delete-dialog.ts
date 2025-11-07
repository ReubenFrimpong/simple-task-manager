import { Component, inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { TaskService } from '../services/task-service';
import { MatButton } from '@angular/material/button';

@Component({
  selector: 'app-task-delete-dialog',
  imports: [
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
    MatButton
  ],
  templateUrl: './task-delete-dialog.html',
  styleUrl: './task-delete-dialog.scss'
})
export class TaskDeleteDialog {
    dialog = inject(MatDialog);
    taskId = inject(MAT_DIALOG_DATA);
    dialogRef = inject(MatDialogRef<TaskDeleteDialog>);
    taskService = inject(TaskService);

  public closeDialog() {
    this.dialogRef.close();
  }

  public confirmDelete() {
    this.taskService.deleteTask(this.taskId).subscribe({
      next: (response) => {
        this.dialogRef.close(response);
      },
      error: (error) => {
        console.error('Failed to delete task:', error);
      }
    });
  }
}
