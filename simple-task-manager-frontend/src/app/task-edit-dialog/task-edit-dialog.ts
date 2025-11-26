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
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { TaskService } from '../services/task-service';
import { Router } from '@angular/router';
import { MatButton } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';


@Component({
  selector: 'app-task-edit-dialog',
  imports: [
    MatDialogActions,
    MatDialogClose,
    MatDialogContent,
    MatDialogTitle,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    FormsModule,
    MatButton,
    MatSelectModule
  ],
  templateUrl: './task-edit-dialog.html',
  styleUrl: './task-edit-dialog.scss'
})
export class TaskEditDialog {
  dialog = inject(MatDialog);
  dialogRef = inject(MatDialogRef<TaskEditDialog>);
  task = inject(MAT_DIALOG_DATA);
  taskService = inject(TaskService);
  error = { title: '', description: '', status: '' };
  router = inject(Router);
  taskStatuses = [
    { value: 'PENDING', viewValue: 'Pending' },
    { value: 'COMPLETED', viewValue: 'Completed' }
  ];


  public closeDialog() {
    this.dialogRef.close();
  }

  public updateTask() {
    this.taskService.updateTask(this.task).subscribe({
      next: (response) => {
        this.dialogRef.close(response);
      },
      error: (error) => {
        if (error.status === 403) {
          localStorage.removeItem('authToken');
          this.router.navigate(['/login']);
          return;
        }
        if (error.status === 400) {
          this.error = error.error;
          return;
        }
      }
    });
  }

}
