package org.rossie.videoPlatform.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Admin.class)
    @JoinColumn(name = "adminId")
    private Admin admin;
    private String title;
    private String description;
    private LocalDate createdAt;
    private String url;

    public Video(String originalFilename) {
        this.title = originalFilename;
    }

    public Video(Long id, String title, String description, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
    }
}
