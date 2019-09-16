package internetshop.service.impl;

import internetshop.dao.ItemDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Item;
import internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public Item delete(Long id) {
        return itemDao.delete(id);
    }

    @Override
    public Item deleteByItem(Item item) {
        return itemDao.deleteByItem(item);
    }
}
