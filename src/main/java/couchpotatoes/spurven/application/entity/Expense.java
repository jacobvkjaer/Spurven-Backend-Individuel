package couchpotatoes.spurven.application.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


//---------------Lombok.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//---------------Lombok.

@Entity //-- Maven -- Et object som vi skal burger i DB-tabel. Fortæller Der skal laves en table af samme navn some object.
public class Expense {
    //---------------Fields / attribute.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private String category;

    private Double amount;

    @Column(length = 255,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate invoiceStart;
    @Column(length = 255,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate invoiceEnd;

    public Expense(String description, String category, Double amount, LocalDate invoiceStart, LocalDate invoiceEnd) {
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.invoiceStart = invoiceStart;
        this.invoiceEnd = invoiceEnd;
    }

    //---------------Fields / attribute.


    //---------------Database dependencies.
    //---------------Database dependencies.

    //---------------Reservation class.
    //---------------Reservation class.

    //---------------Constructor.
    //---------------Constructor.


}
