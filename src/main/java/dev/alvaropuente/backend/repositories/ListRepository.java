package dev.alvaropuente.backend.repositories;

import dev.alvaropuente.backend.models.ItemList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ItemList, Long> {
    boolean existsById(Long list_id);
}
