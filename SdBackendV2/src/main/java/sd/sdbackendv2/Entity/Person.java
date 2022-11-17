package sd.sdbackendv2.Entity;

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
public class Person {
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

    private String username;
    private String userpassword;
    private int role;

    @ManyToMany(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="personDevice",
            joinColumns = {
                    @JoinColumn(name = "person_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="device_id",referencedColumnName = "id")
            })
    private Set<Device> device;

    public void addDevice(Device dev) {
        this.device.add(dev);
        dev.getUsers().add(this);
    }

    public void removeDevice(long devId) {
        Device dev = this.device.stream().filter(t -> t.getId() == devId).findFirst().orElse(null);
        if (dev != null) {
            this.device.remove(dev);
            dev.getUsers().remove(this);
        }
    }
}

