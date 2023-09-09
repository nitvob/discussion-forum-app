package dev.nit.quoraClone;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @JsonSerialize(using = ToStringSerializer.class) // keeps object id as string during json serialization
    private ObjectId id; // objectid of the post

    private String commentBody; // content of the comment

    @DocumentReference
    private List<Comment> comments; // references to sub comments

    @JsonIgnore
    private ObjectId parentCommentId; // ref to parent comment id

    @JsonIgnore
    private ObjectId parentPostId; // ref to parent post id

    public Comment(String commentBody, String parentPostId, String parentCommentId) {
        this.commentBody = commentBody;
        this.parentPostId = parentPostId != null ? new ObjectId(parentPostId) : null;
        this.parentCommentId = parentCommentId != null ? new ObjectId(parentCommentId) : null;
        this.comments = new ArrayList<>();
    }
}
