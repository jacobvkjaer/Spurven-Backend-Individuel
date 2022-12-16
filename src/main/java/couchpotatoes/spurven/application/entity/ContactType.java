package couchpotatoes.spurven.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*@ToString*/
//---------------Lombok.
@Entity
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255,nullable = false)
    private String type;

    @JsonBackReference
    @OneToMany(mappedBy = "contactType", fetch = FetchType.LAZY)
    List<Contact> contacts = new ArrayList<>();

    //not necessary at this point in the development process
    /*public void addContactType(ContactType contactType){
        contacts.add(contactType);
    }*/
    public ContactType(String type) {
        this.type = type;
    }
}
