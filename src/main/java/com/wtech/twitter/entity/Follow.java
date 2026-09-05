package com.wtech.twitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
// Unique constraint ile A kullanıcısının B kullanıcısını sadece 1 kez takip edebilmesini sağlıyoruz
@Table(name = "user_follows", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"follower_id", "following_id"})
})

public class Follow extends EntityBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower; // Takip eden kişi

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    private User following; // Takip edilen kişi
}