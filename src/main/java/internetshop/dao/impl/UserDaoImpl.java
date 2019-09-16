package internetshop.dao.impl;

import internetshop.dao.Storage;
import internetshop.dao.UserDao;
import internetshop.lib.Dao;
import internetshop.model.User;

import java.util.NoSuchElementException;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.users.add(user);
        return user;
    }

    @Override
    public User get(Long id) {
        return Storage.users
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find number with id " + id));
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getId().equals(user.getId())) {
                Storage.users.set(i, user);
                return user;
            }
        }
        throw new NoSuchElementException("Can't find user" + user.getName());
    }

    @Override
    public User delete(Long id) {
        User user = get(id);
        Storage.users.removeIf(s -> s.getId().equals(id));
        return user;
    }

    @Override
    public User deleteByUser(User user) {
        Storage.users.removeIf(s -> s.equals(user));
        return user;
    }
}
