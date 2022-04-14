package com.nelisriebezos.broozercruiserbot.data.dto;

import com.nelisriebezos.broozercruiserbot.utils.DTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TankSession")
public class PersistTankSessionDTO extends DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date = new Date();
    @OneToMany(
            mappedBy = "tankSession",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    @Builder.Default
    private List<PersistTripDTO> tripList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private PersistCarDTO car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersistTankSessionDTO that = (PersistTankSessionDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
