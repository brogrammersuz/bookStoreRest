package uz.brogrammers.bookStoreRest.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import uz.brogrammers.bookStoreRest.user.enums.RoleName;

import java.time.ZonedDateTime;

@Getter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private ZonedDateTime created;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "name")
    private RoleName name;

}
