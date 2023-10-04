package com.userManagement.usermanagementservices.service;

import com.userManagement.usermanagementservices.entity.ResponseMessage;
import com.userManagement.usermanagementservices.entity.Users;
import com.userManagement.usermanagementservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users userSignup(Users user) {
        return userRepository.save(user);
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users getSpecificUser(Long phoneNo){
        return userRepository.findByPhoneNo(phoneNo);
    }

    public ResponseMessage userLogin(Long phoneNo, String password){
        Users currUser = userRepository.findByPhoneNo(phoneNo);
        ResponseMessage res = new ResponseMessage(1, "Error", null);
        if(currUser != null && currUser.getPassword().equals(password)){
            res.setCode(0);
            res.setMessage("Success");
            return res;
        }
        return res;
    }

    public ResponseMessage userUpdate(Users user, Long id){
        Users currUser = userRepository.findById(id).get();
        ResponseMessage res = new ResponseMessage(1, "Error", null);
        if(currUser != null){
            if(!user.getFirstName().equals(currUser.getFirstName())){
                currUser.setFirstName(user.getFirstName());
                res.setUser(currUser);
                res.setCode(0);
                res.setMessage("Success");
            }

            if(!user.getLastName().equals(currUser.getLastName())){
                currUser.setLastName(user.getLastName());
                res.setUser(currUser);
                res.setCode(0);
                res.setMessage("Success");
            }

            if(!user.getPhoneNo().equals(currUser.getPhoneNo())){
                Users tempUser = userRepository.findByPhoneNo(user.getPhoneNo());
                if(tempUser == null){
                    currUser.setPhoneNo(user.getPhoneNo());
                    res.setUser(currUser);
                    res.setCode(0);
                    res.setMessage("Success");
                }
            }
            userRepository.save(currUser);
        }
        return res;
    }

    public ResponseMessage userDelete(Long id){
        Users user = userRepository.findById(id).get();
        ResponseMessage res = new ResponseMessage(1, "Error", null);
        if(user != null){
            userRepository.delete(user);
            res.setCode(0);
            res.setMessage("Success");
        }
        return res;
    }

}
