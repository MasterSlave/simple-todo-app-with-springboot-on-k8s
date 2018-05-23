package com.example.todolist.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "todo_item")
@NoArgsConstructor
@Getter
@Setter
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column
    private boolean completed = false;

    public ToDoItem(String value) {
        this.value = value;
    }
}
