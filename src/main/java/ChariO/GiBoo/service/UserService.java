package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAllUser() { return userRepository.findAll();}


    @Transactional(readOnly = true)
    public User findById(Long UserId){ return userRepository.findById(UserId);}


    @Transactional(readOnly = true)
    public User findByUserName(String UserName){
        return userRepository.findByUserName(UserName);
    }
}
