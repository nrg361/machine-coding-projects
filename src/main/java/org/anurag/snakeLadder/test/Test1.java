package org.anurag.snakeLadder.test;

import org.anurag.snakeLadder.controller.GameController;
import org.anurag.snakeLadder.model.Board;
import org.anurag.snakeLadder.model.Dice;
import org.anurag.snakeLadder.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Random;

public class Test1 {
    GameController gameController;
    @BeforeEach
    void initialize(){
        Board board = new Board(new HashMap<>(), 100);
        Dice dice = new Dice(6);
        BoardService boardService = new BoardService(board, new ArrayDeque<>(), dice, new Random());
        gameController = new GameController(boardService);

        // add players
        gameController.addPlayer("Anurag");
        gameController.addPlayer("Tanya");

        // add snakes
        gameController.addSnakeAndLadder(16, 6);
        gameController.addSnakeAndLadder(48, 26);
        gameController.addSnakeAndLadder(64, 60);
        gameController.addSnakeAndLadder(93, 73);

        // add ladders
        gameController.addSnakeAndLadder(1, 38);
        gameController.addSnakeAndLadder(4, 14);
        gameController.addSnakeAndLadder(9, 31);
        gameController.addSnakeAndLadder(21, 42);
        gameController.addSnakeAndLadder(28, 84);
        gameController.addSnakeAndLadder(51, 67);
        gameController.addSnakeAndLadder(80, 99);
    }

    @Test
    void test1(){
        gameController.startGame();
    }
}
