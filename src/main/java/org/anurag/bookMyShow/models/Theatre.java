package org.anurag.bookMyShow.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Theatre {
    private String theatreId;
    private List<Show> shows;
    private String name;
}
