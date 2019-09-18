package internetshop.service.impl;

import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.service.BucketService;

import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.getItems().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket deleteItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        bucket.deleteItem(itemId);
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        bucket.getItems().clear();
        return bucket;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        return bucket.getItems();
    }

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket getByUser(Long userId) {
        return bucketDao.getByUser(userId);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket delete(Long id) {
        return bucketDao.delete(id);
    }

    @Override
    public Bucket deleteByBucket(Bucket bucket) {
        return bucketDao.deleteByBucket(bucket);
    }
}
