package dev.alvaropuente.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.alvaropuente.backend.models.Item;

@Repository
public interface TodoItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByUserId(Long userId);
	Optional<Item> findByIdAndUserId(Long id, Long userId);
}
