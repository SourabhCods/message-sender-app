package sourabhCods.whatsappsender.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sourabhCods.whatsappsender.dto.UserDTO;
import sourabhCods.whatsappsender.entities.UserEntity;
import sourabhCods.whatsappsender.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    final UserRepository userRepo;
    final ModelMapper modelMapper;

    public UserService(UserRepository userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }


    public UserDTO createNewUser(UserDTO userDto) {
        UserEntity newUserEnt = userRepo.save(modelMapper.map(userDto, UserEntity.class));
        System.out.println(newUserEnt);
        System.out.println(modelMapper.map(newUserEnt, UserDTO.class));
        return modelMapper.map(newUserEnt, UserDTO.class);
    }

    public List<UserDTO> getUserInfo() {
        return userRepo.findAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity , UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUserByFirstName(String fname , String lname) {
            return userRepo.findByFullName(fname, lname)
                    .stream()
                    .map(userEntity -> modelMapper.map(userEntity , UserDTO.class))
                    .collect(Collectors.toList());

//        return modelMapper.map(userRepo.findByFirstName(fname) , UserDTO.class);
    }


    public UserDTO updateUserInfo(UserDTO changes) {
        UserEntity user =  userRepo.findUserById(changes.getId());
        if(changes.getFirstName() != null) user.setFirstName(changes.getFirstName());
        if(changes.getLastName() != null) user.setLastName(changes.getLastName());
        if(changes.getEmail() != null) user.setEmail(changes.getEmail());
        if(changes.getWhatsappNumber() != null) user.setWhatsappNumber(changes.getWhatsappNumber());
        userRepo.save(user);
        return modelMapper.map(user , UserDTO.class);
    }

    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }
}
