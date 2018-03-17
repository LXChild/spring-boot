package me.chong.springboot2_0;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 */
@Repository
public class UserRepository {

    /**
     * 采用内存型存储方式-》Map
     */
    private static final ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();

    private static final AtomicInteger idGenerator = new AtomicInteger();

    /**
     * 保存用户对象
     * @param user {@link User} 对象
     * @return 如果保存成功，则返回<code>true</code>,
     *          否则返回<code>false</code>
     */
    public boolean save(User user) {
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return repository.putIfAbsent(id, user) == null;
    }

    public User findOne(Integer id) {
        return repository.get(id);
    }

    public Collection<User> findAll() {
        return repository.values();
    }
}
