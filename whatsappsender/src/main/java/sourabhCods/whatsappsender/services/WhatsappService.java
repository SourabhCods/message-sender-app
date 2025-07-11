package sourabhCods.whatsappsender.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sourabhCods.whatsappsender.dto.UserDTO;

import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsappService {

    @Value("${whapi.url}")
    private String API_URL;

    @Value("${whapi.token}")
    private String API_TOKEN;

    public String sendWhatsAppMessage(UserDTO userDto) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(API_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("to", userDto.getWhatsappNumber());
        body.put("body", userDto.getFirstName() + "\n" + userDto.getLastName() + "\n" + userDto.getEmail()
        );

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "Failed to send message: " + e.getMessage();
        }
    }
}
