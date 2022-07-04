package bredex.homework.jobboard.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class UrlTest {

    @JsonProperty("listOfURLs")
    private List<String> listOfURLs;
}
