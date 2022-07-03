package bredex.homework.jobboard.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class URLDTO {

    private List<String> listOfURLs;

    public void addURLToList(String urlString) {
        this.listOfURLs.add(urlString);
    }
}
