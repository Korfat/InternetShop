package internetshop.dao.impl;

import internetshop.dao.BucketDao;
import internetshop.dao.Storage;
import internetshop.lib.Dao;
import internetshop.model.Bucket;

import java.util.NoSuchElementException;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.buckets
                .stream()
                .filter(i -> i.getId().equals(bucketId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + bucketId));
    }

    @Override
    public Bucket getByUser(Long userId) {
        return Storage.buckets
                .stream()
                .filter(i -> i.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + userId));
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Storage.buckets.get(i).getId().equals(bucket.getId())) {
                Storage.buckets.set(i, bucket);
                return bucket;
            }
        }
        throw new NoSuchElementException("Can't find item" + bucket.getId());
    }

    @Override
    public Bucket delete(Long id) {
        Bucket bucket = get(id);
        Storage.buckets.removeIf(s -> s.getId().equals(id));
        return bucket;
    }

    @Override
    public Bucket deleteByBucket(Bucket bucket) {
        Storage.buckets.removeIf(s -> s.equals(bucket));
        return bucket;
    }
}
