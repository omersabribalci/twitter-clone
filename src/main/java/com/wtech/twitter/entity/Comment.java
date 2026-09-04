package com.wtech.twitter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments", schema = "public")
@SQLDelete(sql = "UPDATE comments SET is_deleted = true WHERE id = ?") // silme önüne barikat
@SQLRestriction("is_deleted = false") // filtreleme
public class Comment extends EntityBase {

    @Column(name = "content")
    @NotBlank(message = "Content cannot be empty!")
    @Size(max = 280)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
