package ChariO.GiBoo.service;

import ChariO.GiBoo.domain.User;
import ChariO.GiBoo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    //private final User user;

    //@Transactional
    //public User userSave(user) {return userRepository.save(user);}

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findOne(Long userid) { return userRepository.findOne(userid); }

    /**TEST용**/
    public List<User> findOne1(Long userid, int offset, int limit) { return userRepository.findOne1(userid, offset, limit); }

    @Transactional
    public User updateOne(Long u_id, String u_name, String u_email, String u_phone){
        return userRepository.update(u_id, u_name, u_email, u_phone);
    }

    @Transactional
    public void deleteOne(Long u_id) {
        userRepository.delete(u_id);
    }
    //public post
    //public save?
}
