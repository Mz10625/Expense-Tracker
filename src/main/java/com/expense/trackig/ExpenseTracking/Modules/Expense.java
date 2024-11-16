package com.expense.trackig.ExpenseTracking.Modules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "expense")
public class Expense {
    @Id
    private String id;
    private String category;
    private String item;
    private Date date;
    private double amount;
    private String paymentMethod;
    private String Note;
}
