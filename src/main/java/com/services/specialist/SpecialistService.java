package com.services.specialist;

import com.DAO.specialist.SpecialistRepository;
import com.DAO.UserRepository;
import com.models.specialist.Specialist;
import com.models.entity.specialist.SpecialistEntity;
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

    public void changePassword(SpecialistEntity specialist, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity userEntity = specialist.getUserEntity();
        userEntity.setPassword(passwordEncoder.encode(password));

        userRepository.save(userEntity);
    }

    public void changeSpecialist(SpecialistEntity specialist, SpecialistEntity specialistEntity){
        specialist.setName(specialistEntity.getName());
        specialist.setSurname(specialistEntity.getSurname());

        specialistRepository.save(specialist);
    }
}
