package internetshop.service;

import internetshop.model.Item;

import java.util.List;

public interface ItemService {
    Item create(Item item);

    Item get(Long id);

    List<Item> getAll();

    Item update(Item item);

    Item delete(Long id);

    Item deleteByItem(Item item);
}
