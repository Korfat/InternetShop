package internetshop.dao;

import internetshop.model.User;

public interface UserDao {
    User create(User item);

    User get(Long id);

    User update(User item);

    User delete(Long id);

    User deleteByUser(User item);
}
