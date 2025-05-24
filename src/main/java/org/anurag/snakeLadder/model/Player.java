package org.anurag.snakeLadder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Player {
    private String id;
    @Setter
    private Boolean hasWon;
    @Setter
    private Integer currentCell;
    private String name;
}
