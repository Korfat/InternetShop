package internetshop;

import internetshop.lib.Inject;
import internetshop.lib.Injector;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.User;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;

public class Main {
    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    public static void main(String[] args) {
        Item firstItem = new Item("firts", 2.12);
        Item secondItem = new Item("second", 3.92);
        Item thirdItem = new Item("third", 3.92);
        itemService.create(firstItem);
        itemService.create(secondItem);
        itemService.create(thirdItem);

        User user = new User("Vasya");
        userService.create(user);

        Bucket bucket = new Bucket(user);
        bucketService.create(bucket);
        bucketService.addItem(bucket.getId(), firstItem.getId());
        bucketService.addItem(bucket.getId(), secondItem.getId());
        bucketService.addItem(bucket.getId(), thirdItem.getId());

        orderService.completeOrder(bucketService.getAllItems(bucket.getId()), user.getId());

        System.out.println(bucketService.getAllItems(bucket.getId()).get(0).getName());
    }
}
