package couchpotatoes.spurven.application.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
//---------------Lombok.
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
//---------------Lombok.
@Entity
public class Contact {
    //---------------Fields / attribute.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 255,nullable = false)
    private String name;
    @Column(length = 255,nullable = false)
    private String phone;
    @Column(length = 255,nullable = false)
    private String email;
    /*@Column(length = 255,nullable = false)*/

    @JsonManagedReference
    @ManyToOne
    /*@JoinColumn(name = "typeIdj")*/
    ContactType contactType;
    //---------------Fields / attribute.


    //---------------Database dependencies.
    //---------------Database dependencies.

    //---------------Reservation class.
    //---------------Reservation class.


    //---------------Constructor.
    public Contact(String name, String phone, String email, ContactType contactType) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.contactType = contactType;
    }
    //---------------Constructor.




}
