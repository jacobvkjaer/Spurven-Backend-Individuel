package couchpotatoes.spurven.application.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

//---------------Lombok.
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//---------------Lombok.

@Entity //-- Maven -- Et object som vi skal burger i DB-tabel. Fortæller Der skal laves en table af samme navn some object.
public class Event {
    //---------------Fields / attribute.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255,nullable = false)
    private String title;
    @Column(length = 255,nullable = false)
    private String description;

    @Column(length = 255,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime start;
    @Column(length = 255,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime end;

    public Event(String title, String description, LocalDateTime start, LocalDateTime end) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;

    }

    //---------------Fields / attribute.





    //---------------Database dependencies.
    //---------------Database dependencies.

    //---------------Reservation class.
    //---------------Reservation class.

    //---------------Constructor.
    //---------------Constructor.

}
