package com.asm3.prj321x.thienhtfx17332.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private  String name;

    @JsonManagedReference(value = "role") //fix lỗi khóa ngoại (https://stackoverflow.com/questions/52782071/spring-boot-error-cannot-call-senderror-after-the-response-has-been-committ)
    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
