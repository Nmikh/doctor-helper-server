package com.services.doctor;

import com.DAO.doctor.DoctorRepository;
import com.DAO.doctor.SpecializationRepository;
import com.DAO.UserRepository;
import com.models.doctor.Doctor;
import com.models.entity.doctor.DoctorEntity;
import com.models.entity.doctor.SpecializationEntity;
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

    @Autowired
    SpecializationRepository specializationRepository;

    public boolean registerDoctor(Doctor doctor, Long specializationId) {
        SpecializationEntity specialization = specializationRepository.getOne(specializationId);

        if (userRepository.findByLogin(doctor.getLogin()) == null
                && specialization != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            UserEntity user = userRepository.save(new UserEntity(doctor.getLogin(),
                    passwordEncoder.encode(doctor.getPassword())));

            doctorRepository.save(
                    new DoctorEntity(doctor.getName(),
                            doctor.getSurname(), user, specialization));

            return true;
        }
        return false;
    }

    public DoctorEntity findDoctorByLogin(String login) {
        UserEntity user = userRepository.findByLogin(login);
        return doctorRepository.findByUserEntity(user);
    }

    public void changePassword(DoctorEntity doctor, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity userEntity = doctor.getUserEntity();
        userEntity.setPassword(passwordEncoder.encode(password));

        userRepository.save(userEntity);
    }

    public void changeDoctor(DoctorEntity doctor, DoctorEntity doctorEntity, Long specializationId){
        SpecializationEntity specialization = specializationRepository.getOne(specializationId);

        doctor.setName(doctorEntity.getName());
        doctor.setSurname(doctorEntity.getSurname());
        doctor.setSpecialization(specialization);

        doctorRepository.save(doctor);
    }
}
