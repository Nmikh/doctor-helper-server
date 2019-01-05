package com.services;

import com.DAO.SpecialistRepository;
import com.DAO.UserRepository;
import com.models.Specialist;
import com.models.entity.SpecialistEntity;
import com.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SpecialistService {

    @Autowired
    SpecialistRepository specialistRepository;

    @Autowired
    UserRepository userRepository;

    public boolean registerSpecialist(Specialist specialist) {
        if (userRepository.findByLogin(specialist.getLogin()) == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            UserEntity user = userRepository.save(new UserEntity(specialist.getLogin(),
                    passwordEncoder.encode(specialist.getPassword())));

            specialistRepository.save(
                    new SpecialistEntity(specialist.getName(),
                            specialist.getSurname(), user));

            return true;
        }
        return false;
    }

    public SpecialistEntity findSpecialistByLogin(String login){
        UserEntity user = userRepository.findByLogin(login);
        return specialistRepository.findByUserEntity(user);
    }
}
