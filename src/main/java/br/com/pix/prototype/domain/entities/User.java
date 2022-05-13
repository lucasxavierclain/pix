package br.com.pix.prototype.domain.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class User {


    private String id;

    private KeyType keyType;

    private  String keyValue;

    private String accountType;

    private String agencyNumber;

    private String accountNumber;

    private String accountHolderFirstName;

    private String accountHolderLastName;

    private String personType;

    private Integer activationTime;
}
