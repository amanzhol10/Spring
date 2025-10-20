package kz.bitlab.springboot.springbootdemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apps")
public class ApplicationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "userName",length = 200)
    private String userName;

    @Column(name = "courseName",length = 200)
    private String courseName;

    @Column(name = "commentary",length = 200)
    private String commentary;

    @Column(name = "phone",length = 200)
    private String phone;

    @Column(name = "handled")
    private boolean handled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Courses course;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Operators> operators;
}
