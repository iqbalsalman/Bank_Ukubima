package iqbal.salman.bank.ukubima.bankukubima.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles",schema = "security")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_security_seq",
            schema = "security",
            allocationSize = 1,
            initialValue = 1,
            sequenceName = "role_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_security_seq")
    @Column(name = "role_id", nullable = false, unique = true)
    private Long id;
    @Column(name="authority")
    private String role;


}
