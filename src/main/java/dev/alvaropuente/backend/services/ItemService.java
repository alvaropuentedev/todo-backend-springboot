package dev.alvaropuente.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.alvaropuente.backend.models.Item;
import dev.alvaropuente.backend.repositories.ItemRepository;
import dev.alvaropuente.backend.repositories.ItemlistRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemlistRepository listsRepository;

    public ItemService(ItemRepository itemRepository, ItemlistRepository listsRepository) {
        this.itemRepository = itemRepository;
        this.listsRepository = listsRepository;
    }

    /**
     * Get all items from a list
     *
     * @param list_id
     * @return
     */
    @Transactional(readOnly = true)
    public List<Item> getAllItemsByListId(Long list_id) {
        if (listsRepository.existsById(list_id)) {
            return itemRepository.findByListIdOrderByIdAsc(list_id);
        } else {
            // Handle the case where the list does not exist, for example, by throwing an exception
            throw new NoSuchElementException("The list with id " + list_id + " does not exist.");
        }
    }

    /**
     * Create item
     *
     * @param list_id
     * @param item
     * @return
     */
    @Transactional
    public Item createItem(Long list_id, Item item) {
        return listsRepository.findById(list_id).map(list -> {
            item.setLists(list);
            item.setCreationDate(LocalDateTime.now());
            return itemRepository.save(item);
        }).orElseThrow(() -> new IllegalArgumentException("List not found"));
    }

    /**
     * Update item description
     *
     * @param list_id
     * @param item_id
     * @param item
     * @return
     */
    @Transactional
    public Item updateItem(Long list_id, Long item_id, Item item) {
        return itemRepository.findByListIdAndId(list_id, item_id).map(existingItem -> {
            existingItem.setDescription(item.getDescription());
            existingItem.setCreationDate(LocalDateTime.now());
            return itemRepository.save(existingItem);
        }).orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    /**
     * Delete item by user and item ID
     */
    @Transactional
    public void deleteItem(Long item_id) {
        itemRepository.deleteItemById(item_id);
    }

}
