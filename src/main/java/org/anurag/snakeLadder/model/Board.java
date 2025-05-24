package org.anurag.snakeLadder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class Board {
    private Map<Integer, Integer> snakeAndLadder;
    private Integer boardSize;
}
