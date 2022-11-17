package sd.sdbackendv2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String description;
    private String address;
    private int maxHconsumption;

    private String location;

    @ManyToMany(mappedBy = "device",fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Person> users;

    @OneToMany(mappedBy="device")
    private Set<Consumption> consumptions;
}
