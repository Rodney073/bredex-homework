package bredex.homework.jobboard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    @Id
    private String positionId;
    private String name;
    private String location;
}
