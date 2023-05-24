package com.example.models;

import com.example.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "task")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String desc;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Task(String title, String desc) {
        this.title = title;
        this.desc = desc;

    }

    public Task(String title, String desc, TaskStatus status, Person person) {
        this.title = title;
        this.desc = desc;
        this.status = status;
        this.person = person;
    }

    public Task(Long id, String title, String desc, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.status = status;
    }
}
