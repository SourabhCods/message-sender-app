package sourabhCods.whatsappsender.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserEntity {
    @Id
    private String id;
    private String firstName; // mongoose id
    private String lastName;
    private String email;
    private String whatsappNumber;
}
