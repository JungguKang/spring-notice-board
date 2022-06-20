package com.study.board.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String userId;
    private String password;
    private String emailAddress;
    private String phoneNumber;
}
