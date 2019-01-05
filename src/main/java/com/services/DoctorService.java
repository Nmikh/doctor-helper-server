package com.services;

import com.DAO.DoctorRepository;
import com.DAO.UserRepository;
import com.models.Doctor;
import com.models.entity.DoctorEntity;
import com.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    public boolean registerDoctor(Doctor doctor) {
        if (userRepository.findByLogin(doctor.getLogin()) == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            UserEntity user = userRepository.save(new UserEntity(doctor.getLogin(),
                    passwordEncoder.encode(doctor.getPassword())));

            doctorRepository.save(
                    new DoctorEntity(doctor.getName(),
                            doctor.getSurname(), user));

            return true;
        }
        return false;
    }

    public DoctorEntity findDoctorByLogin(String login){
        UserEntity user = userRepository.findByLogin(login);
        return doctorRepository.findByUserEntity(user);
    }
}
