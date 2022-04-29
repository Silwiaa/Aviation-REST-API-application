package com.smart4aviation.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "CARGO_ENTITY")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoEntity {
    @Id
    @NotNull
    @Column(name = "CARGO_ENTITY_ID", unique = true)
    private long cargoEntityId;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "FLIGHT_ID")
    private FlightEntity flightEntity;

    @OneToMany(
            targetEntity = Baggage.class,
            mappedBy = "cargoEntity",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    private List<Baggage> baggageList;

    @OneToMany(
            targetEntity = Cargo.class,
            mappedBy = "cargoEntity",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    private List<Cargo> cargoList;
}
