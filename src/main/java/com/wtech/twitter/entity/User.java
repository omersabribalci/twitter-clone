package com.wtech.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", schema = "public")
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id = ?") // silme önüne barikat
@SQLRestriction("is_deleted = false") // filtreleme
public class User extends EntityBase {
    @NotBlank(message = "Name cannot be empty!")
    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @Column(name = "bio")
    @Size(max = 160)
    private String bio;

    @NotBlank(message = "Username cannot be empty!")
    @Column(name = "user_name", unique = true)
    @Size(max = 15)
    private String userName;

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "Please enter a valid email address!")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 6)
    @Column(name = "password")
    private String password;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tweet> tweets;
}
