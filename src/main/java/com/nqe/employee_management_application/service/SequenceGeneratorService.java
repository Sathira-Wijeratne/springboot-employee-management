package com.nqe.employee_management_application.service;

// This class is used to generate the sequence number for the employee id rather than using the default MongoDB id.

import com.nqe.employee_management_application.model.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service // marks this class as a Spring service
public class SequenceGeneratorService {
    private MongoOperations mongoOperations; // is an Interface for MongoDB operations, it is needed to interact with the database

    @Autowired // Automatically injects the MongoOperations bean into the SequenceGeneratorService class, enables the class to interact with the MongoDB database without manually creating an instance of MongoOperations
    public SequenceGeneratorService(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName){
        DatabaseSequence counter = mongoOperations.findAndModify(
                query(where("_id").is(seqName)), // find the document with the given sequence name
                new Update().inc("seq", 1), // Increment the sequence value by 1
                options().returnNew(true).upsert(true), // Return the updated document, create if not found
                DatabaseSequence.class); // The class type of the document

        // Return the updated sequence value, or 1 is returned if the document is null
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
