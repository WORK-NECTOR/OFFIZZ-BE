package com.worknector.offizz.domain.user.domain.entity;

import com.worknector.offizz.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.worknector.offizz.domain.user.domain.entity.UserStatusType.DELETED;
import static com.worknector.offizz.domain.user.domain.entity.UserStatusType.USABLE;
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

    private String email;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    @Builder.Default
    private UserStatusType status = USABLE;

    private String pw;

    public boolean isDeleted() {
        return this.status.equals(DELETED);
    }

    public void changeStatusToDeleted() {
        this.status = DELETED;
    }

    public void updateNickName(String nickName) {
        this.nickName = nickName;
    }
}
