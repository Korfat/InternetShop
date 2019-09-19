package internetshop.dao.impl;

import internetshop.dao.ItemDao;
import internetshop.dao.Storage;
import internetshop.lib.Dao;
import internetshop.model.Item;

import java.util.List;
import java.util.NoSuchElementException;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find number with id " + id));
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public Item update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getId().equals(item.getId())) {
                Storage.items.set(i, item);
                return item;
            }
        }
        throw new NoSuchElementException("Can't find item" + item.getName());
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        Storage.items.removeIf(s -> s.getId().equals(id));
        return item;
    }

    @Override
    public Item deleteByItem(Item item) {
        Storage.items.removeIf(s -> s.equals(item));
        return item;
    }
}
