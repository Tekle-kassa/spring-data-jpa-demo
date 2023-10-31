package com.example.demo;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name="StudentIdCard")
@Table(
        name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="student_id_card_number_unique",
                        columnNames = "card_number")
        }
)
public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name="student_card_id_sequence",
            sequenceName = "student_card_id_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator =  "student_card_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @Column(
            name = "card_number",
            nullable = false,
            length = 15

    )
    private String cardNumber;

    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
