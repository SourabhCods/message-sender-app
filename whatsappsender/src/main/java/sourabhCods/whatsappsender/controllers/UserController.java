package sourabhCods.whatsappsender.controllers;


import org.springframework.web.bind.annotation.*;
import sourabhCods.whatsappsender.dto.UserDTO;
import sourabhCods.whatsappsender.services.UserService;
import sourabhCods.whatsappsender.services.WhatsappService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    UserService userService;
    WhatsappService whatsappService;

    public UserController(UserService userService, WhatsappService whatsappService) {
        this.userService = userService;
        this.whatsappService = whatsappService;
    }

    // get http
    @GetMapping
    public List<UserDTO> getUserInfo(){
        return userService.getUserInfo();
    }

    // post http
    @PostMapping // by default the route/endpoint/path is going to be  '/'
    public String createNewUser(@RequestBody UserDTO userDto){
        System.out.println(userService.createNewUser(userDto));
        return whatsappService.sendWhatsAppMessage(userDto);
    }

//    @GetMapping(path = "/user/{fname}/{lname}")
//    public List<UserDTO> getUserByFirstName(@PathVariable String fname, @PathVariable String lname){
//        return userService.getUserByFirstName(fname, lname);
//    }

    // patch/put http
    @PatchMapping
    public UserDTO updateUserInfo(@RequestBody UserDTO changes){
        return userService.updateUserInfo(changes);
    }

    // delete http
    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }


}
