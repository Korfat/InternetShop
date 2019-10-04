package internetshop.service;

import internetshop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Optional<Item> create(Item item);

    Optional<Item> get(Long id);

    Optional<List<Item>> getAll();

    Optional<Item> update(Item item);

    void delete(Long id);

    Optional<Item> deleteByItem(Item item);
}
