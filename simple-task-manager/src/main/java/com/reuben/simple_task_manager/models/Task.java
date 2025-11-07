package com.reuben.simple_task_manager.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task extends BaseEntity {

  @Column(nullable = false)
  private String title;

  private String description;

  @Column(nullable = false)
  private String status;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
