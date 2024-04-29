package com.communitter.api.post;


import com.communitter.api.templates.DataField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post_fields")
public class PostField {

    @Id
    @SequenceGenerator(
            name="post_fields_sequence",
            sequenceName = "post_fields_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "post_fields_sequence")
    private Long id;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    @JsonBackReference("post-fields")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "data_field_id",nullable = false)
    private DataField dataField;

}
