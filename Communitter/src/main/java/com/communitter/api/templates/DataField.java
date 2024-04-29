package com.communitter.api.templates;

import com.communitter.api.user.Role;
import com.communitter.api.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="data_field")
public class DataField {
    @Id
    @SequenceGenerator(
            name="data_field_sequence",
            sequenceName = "data_field_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "data_field_sequence")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean isRequired;
    @ManyToOne
    @JoinColumn(name = "data_field_type_id")
    private DataFieldType dataFieldType;
    @ManyToOne
    @JoinColumn(name = "template_id")
    @JsonBackReference("template-datafields")
    private Template template;
}
