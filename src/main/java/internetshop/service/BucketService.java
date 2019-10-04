package internetshop.service;

import internetshop.model.Bucket;
import internetshop.model.Item;

import java.util.List;
import java.util.Optional;

public interface BucketService {
    Optional<Bucket> addItem(Long bucketId, Long itemId);

    Optional<Bucket> deleteItem(Long bucketId, Long itemId);

    Optional<Bucket> clear(Long bucketId);

    Optional<List<Item>> getAllItems(Long bucketId);

    Optional<Bucket> create(Bucket bucket);

    Optional<Bucket> get(Long id);

    Optional<Bucket> getByUser(Long userId);

    Optional<Bucket> update(Bucket bucket);

    void delete(Long id);
}
