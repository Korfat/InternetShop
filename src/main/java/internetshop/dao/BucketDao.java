package internetshop.dao;

import internetshop.model.Bucket;

import java.util.Optional;

public interface BucketDao {
    Optional<Bucket> create(Bucket bucket);

    Optional<Bucket> get(Long bucketId);

    Optional<Bucket> getByUser(Long userId);

    Optional<Bucket> update(Bucket bucket);

    void delete(Long id);

    Optional<Bucket> addItem(Long bucketId, Long itemId);

    Optional<Bucket> deleteItem(Long bucketId, Long itemId);

    Optional<Bucket> clear(Long bucketId);
}
