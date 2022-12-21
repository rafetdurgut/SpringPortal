package com.rafetdurgut.springportal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String Description;

    private Long createdAt;
    private Long updatedAt;

    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "post")
    private Collection<Comment> comments;
}
