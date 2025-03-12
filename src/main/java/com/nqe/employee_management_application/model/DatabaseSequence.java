package com.nqe.employee_management_application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// This class is used to generate the sequence number for the employee id rather than using the default MongoDB id.

@Document(collection = "database_sequences")  // tells MongoDB that instances of this class represent documents in the database_sequences collection
public class DatabaseSequence {
    // properties
    @Id //marks the id field as the primary key
    private String id;
    private long seq;

    // constructor
    public DatabaseSequence(){}

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
