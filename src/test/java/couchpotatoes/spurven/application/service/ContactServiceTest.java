package couchpotatoes.spurven.application.service;

import couchpotatoes.spurven.application.dto.ContactRequest;
import couchpotatoes.spurven.application.entity.Contact;
import couchpotatoes.spurven.application.entity.ContactType;
import couchpotatoes.spurven.application.repository.ContactRepository;
import couchpotatoes.spurven.application.repository.ContactTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    ContactRepository contactRepo;

    @Mock
    ContactTypeRepository contactTypeRepo;

    @Autowired
    ContactService contactServ;

    @BeforeEach
    public void setup(){
        contactServ = new ContactService(contactRepo, contactTypeRepo);
    }

    @Test
    void getAllContacts() {
        Mockito.when(contactRepo.findAll()).thenReturn(List.of(
           new Contact("John","40404040","john@mail.something",new ContactType()),
           new Contact("Bingo","50505050","bingo@mail.something",new ContactType())
        ));

        List<Contact> contacts = contactServ.getAllContacts();

        assertEquals(2, contacts.size());
        assertEquals("John",contacts.get(0).getName());
    }

    @Test
    void createContact() {
        ContactType contactType = new ContactType("something");
        contactType.setId(50);
        Contact contact = new Contact("John","40404040","john@mail.something",contactType);

        Mockito.when(contactTypeRepo.findById(any())).thenReturn(Optional.of(contactType));
        Mockito.when(contactRepo.save(any(Contact.class))).thenReturn(contact);

        Contact createdContact = contactServ.createContact(contact.getName(),contact.getPhone(),contact.getEmail(), contact.getContactType().getId());

        assertEquals("John", createdContact.getName());
        assertEquals(50, createdContact.getContactType().getId());
    }

    @Test
    void editContact() {
        Contact contact = new Contact("John","40404040","john@mail.something",new ContactType());
        contact.setId(50);
        ContactType contactType = new ContactType("something");
        contactType.setId(50);

        Mockito.when(contactRepo.save(any(Contact.class))).thenReturn(contact);
        Mockito.when(contactRepo.findById(any())).thenReturn(Optional.of(contact));
        Mockito.when(contactTypeRepo.findById(any())).thenReturn(Optional.of(contactType));


        ContactRequest request = new ContactRequest("John","40404040","john@mail.something",50);
        Contact editedContact = contactServ.editContact(request,50);

        assertEquals(50,editedContact.getId());
    }
}