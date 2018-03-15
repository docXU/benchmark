package com.example.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.repository.UserJpaRepository;
import com.example.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    public List<User> findByName(String name) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        List<User> userList1 = userRepository.findByName1(name);
        List<User> userList2 = userRepository.findByName2(name);
        List<User> userList3 = userRepository.findByNameAndTel(name, "123");
        logger.info("userList1:" + userList1);
        logger.info("userList2:" + userList2);
        logger.info("userList3:" + userList3);
        return userRepository.findByName(name);
    }

    public void saveUser(User book) {
        userJpaRepository.save(book);
    }

    @Cacheable("users")
    public User findOne(long id) {
        System.out.println("Cached Pages");
        return userJpaRepository.findOne(id);
    }

    public void delete(long id) {
        userJpaRepository.delete(id);
    }
}
