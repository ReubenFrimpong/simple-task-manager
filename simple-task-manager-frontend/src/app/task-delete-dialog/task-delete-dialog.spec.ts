import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskDeleteDialog } from './task-delete-dialog';

describe('TaskDeleteDialog', () => {
  let component: TaskDeleteDialog;
  let fixture: ComponentFixture<TaskDeleteDialog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskDeleteDialog]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaskDeleteDialog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
