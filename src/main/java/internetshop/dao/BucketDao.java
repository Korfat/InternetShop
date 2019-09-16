package internetshop.dao;

import internetshop.model.Bucket;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket get(Long bucketId);

    Bucket update(Bucket bucket);

    Bucket delete(Long id);

    Bucket deleteByBucket(Bucket bucket);
}
