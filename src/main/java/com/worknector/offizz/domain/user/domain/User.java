package com.worknector.offizz.domain.user.domain;

import com.worknector.offizz.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.worknector.offizz.domain.user.domain.UserStatusType.DELETED;
import static com.worknector.offizz.domain.user.domain.UserStatusType.USABLE;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private Long socialId;

    private String nickName;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private UserStatusType status = USABLE;

    public boolean isDeleted() {
        return this.status.equals(DELETED);
    }

    public void changeStatusToDeleted() {
        this.status = DELETED;
    }
}
