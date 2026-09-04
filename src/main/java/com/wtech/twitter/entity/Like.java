package com.wtech.twitter.entity;

import jakarta.persistence.*;
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
@Table(name = "tweet_likes", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "tweet_id"})
})
@SQLDelete(sql = "UPDATE tweet_likes SET is_deleted = true WHERE id = ?") // silme önüne barikat
@SQLRestriction("is_deleted = false") // filtreleme
public class Like extends EntityBase {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;
}
