package org.anurag.snakeLadder.controller;

import lombok.AllArgsConstructor;
import org.anurag.snakeLadder.service.BoardService;

import java.util.Objects;

@AllArgsConstructor
public class GameController {
    private BoardService boardService;

    public void startGame(){
        while(Objects.nonNull(boardService.getNextPlayer())){
            boardService.movePlayer();
            if(boardService.hasPlayerWon()){
                boardService.removeCurrentPlayer();
            }else{
                boardService.addCurrentPlayerToLast();
            }
        }
    }

    public void addSnakeAndLadder(Integer from, Integer to){
        boardService.addSnakeAndLadder(from, to);
    }

    public void addPlayer(String playerName){
        boardService.addPlayer(playerName);
    }
}
