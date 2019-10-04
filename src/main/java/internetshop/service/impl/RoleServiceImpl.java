package internetshop.service.impl;

import internetshop.dao.RoleDao;
import internetshop.lib.Inject;
import internetshop.lib.Service;
import internetshop.model.Role;
import internetshop.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Inject
    private static RoleDao roleDao;

    @Override
    public Optional<Role> create(Role role) {
        return roleDao.create(role);
    }

    @Override
    public Optional<Role> get(Long id) {
        return roleDao.get(id);
    }

    @Override
    public Optional<Role> update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
