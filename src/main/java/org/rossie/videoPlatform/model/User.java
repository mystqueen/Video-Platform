package org.rossie.videoPlatform.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "User_sequence",
            sequenceName = "User_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "User_sequence"
    )
    private Long id;
    private String username;
    private String email;
    private String password;
    @CreationTimestamp
    private Instant createdAt;
    private boolean accountVerified;
    private UUID authToken;
    private UUID resetToken;
    private LocalDateTime resetTokenExpire;
    private LocalDateTime authTokenExpire;
    private UUID sessionToken;
    private LocalDateTime sessionTokenExpire;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

}
