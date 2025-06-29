package org.anurag.snakeLadder.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anurag.snakeLadder.model.*;

import java.util.*;

@AllArgsConstructor
@Slf4j
public class BoardService {
    private Board board;
    private Deque<Player> players;
    private Dice dice;
    private Random random;

    public void movePlayer(){
        Player player = players.getFirst();
        String name = player.getName();
        Integer rolledNumber = rollDice();
        log.info("Player {} rolled a dice with {}",player.getName(), rolledNumber);
        Integer nextCell = player.getCurrentCell() + rolledNumber;

        if(nextCell.equals(board.getBoardSize())){
            log.info("Player {} has won the game!", name);
            player.setHasWon(Boolean.TRUE);
            player.setCurrentCell(nextCell);
        }else if(nextCell > board.getBoardSize()){
            log.info("Move out of board, skipping.");
        }else{
            // check for snakes, ladders
            if(board.getSnakeAndLadder().containsKey(nextCell)){
                Integer finalCell = board.getSnakeAndLadder().get(nextCell);
                if(nextCell > finalCell){
                    log.info("Player {} reached cell {} after getting bitten by a snake.", name, finalCell);
                }else{
                    log.info("Player {} reached cell {} after climbing a ladder.", name, finalCell);
                }
                player.setCurrentCell(finalCell);
            }else{
                log.info("Player {} reached the cell {}.", name, nextCell);
                player.setCurrentCell(nextCell);
            }
        }
    }

    private Integer rollDice(){
        return 1 + random.nextInt(dice.getMAX_NUM());
    }

    public Boolean hasPlayerWon(){
        return players.getFirst().getHasWon();
    }

    public void addSnakeAndLadder(Integer from, Integer to){
        board.getSnakeAndLadder().put(from, to);
    }

    public String addPlayer(String playerName){
        Player player = Player.builder()
                .id(UUID.randomUUID().toString()).currentCell(0).name(playerName).hasWon(Boolean.FALSE).build();
        players.addLast(player);
        return player.getId();
    }

    public String getNextPlayer(){
        if(players.isEmpty())
            return null;
        return players.getFirst().getId();
    }

    public void removeCurrentPlayer(){
        players.removeFirst();
    }

    public void addCurrentPlayerToLast(){
        Player player = players.getFirst();
        removeCurrentPlayer();
        players.addLast(player);
    }
}
