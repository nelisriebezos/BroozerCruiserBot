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
@Table(name = "Trip")
public class PersistTripDTO extends DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int amountOfKm = 0;
    private Date date = new Date();
    @ElementCollection
    @CollectionTable(name = "personlist", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "person")
    @Builder.Default
    private List<String> personList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersistTripDTO that = (PersistTripDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
