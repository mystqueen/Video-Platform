package org.rossie.videoPlatform.repository;

import org.rossie.videoPlatform.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    // Custom query to find next and previous videos by id
    Video findFirstByIdGreaterThan(Long id);

    Video findFirstByIdLessThanOrderByIdDesc(Long id);

    @Override
    Optional<Video> findById(Long aLong);
}
