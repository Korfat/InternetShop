package internetshop.lib;

import internetshop.Main;
import internetshop.service.impl.BucketServiceImpl;
import internetshop.service.impl.ItemServiceImpl;
import internetshop.service.impl.OrderServiceImpl;
import internetshop.service.impl.UserServiceImpl;

import java.lang.reflect.Field;

public class Injector {
    public static void injectDependency() throws IllegalAccessException {
        inject(ItemServiceImpl.class.getDeclaredFields());
        inject(BucketServiceImpl.class.getDeclaredFields());
        inject(OrderServiceImpl.class.getDeclaredFields());
        inject(UserServiceImpl.class.getDeclaredFields());
        inject(Main.class.getDeclaredFields());
    }

    private static void inject(Field[] fields) throws IllegalAccessException {
        for (Field field : fields) {
            if (field.getDeclaredAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                field.set(null, AnnotatedClassMap.getImplementation(field.getType()));
            }
        }
    }
}
