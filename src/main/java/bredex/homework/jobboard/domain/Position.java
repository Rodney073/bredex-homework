package bredex.homework.jobboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Getter
@Setter
@NoArgsConstructor
public class Position {

    @Id
    private Long positionId;
    private String name;
    private String location;
    private String URL;

    public Position(Long positionId, String name, String location) {
        this.positionId = positionId;
        this.name = name;
        this.location = location;
    }

    public Position validatePositionName(String positionName) {
        if (positionName.length() > 50) {
            throw new InvalidPositionNameException("The position name can't be longer than 50 characters!");
        }
        if (positionName.equals("")) {
            throw new InvalidPositionNameException("The position name can not be empty!");
        }
        return this;
    }

    public Position validateLocationName(String locationName) {
        if (locationName.length() > 50) {
            throw new InvalidLocationException("The location name can't be longer than 50 characters!");
        }
        if (locationName.equals("")) {
            throw new InvalidLocationException("The location name can not be empty!");
        }
        return this;
    }

    public String getPositionURL() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRequestURL().toString();
    }

}
