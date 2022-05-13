package br.com.pix.prototype.application.dtos;

import br.com.pix.prototype.domain.entities.KeyType;
import br.com.pix.prototype.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String id;
    private KeyType keyType;
    private String keyValue;
    private String firstName;
    private String lastName;

    public UserDTO(User user){
        this.id= user.getId();
        this.keyType=user.getKeyType();
        this.keyValue=user.getKeyValue();
        this.firstName=user.getAccountHolderFirstName();
        this.lastName=user.getAccountHolderLastName();
    }
}
