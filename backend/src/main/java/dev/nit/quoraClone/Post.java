package dev.nit.quoraClone;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @JsonSerialize(using = ToStringSerializer.class) // keeps object id as string during json serialization
    private ObjectId id; // objectid of the post

    private String title; // post title

    private String description; // post description

    private List<String> topics; // list of topics

    @DocumentReference
    List<Comment> comments; // list of refs to comments

    public Post(String title, String description, List<String> topics) {
        this.title = title;
        this.description = description;
        this.topics = topics;
        this.comments = new ArrayList<>();
    }
}
