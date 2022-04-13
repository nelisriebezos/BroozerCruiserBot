package com.nelisriebezos.broozercruiserbot.data.dto;

import com.nelisriebezos.broozercruiserbot.utils.DTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Car")
public class PersistCarDTO extends DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int kmCounter = 0;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<PersistTankSessionDTO> tankSessionList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersistCarDTO that = (PersistCarDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
