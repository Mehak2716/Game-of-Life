package org.example.gameoflife;

import org.example.modals.Board;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {

    private final int rows;

    private final int columns;

    private final int seedPercent;
    private Board board;

    public Game(int rows, int columns, int seedPercent) {
        if(rows<0 || columns<0 || seedPercent<0)
            throw new IllegalArgumentException("Provided value must be non-negative");

        this.rows = rows;
        this.columns = columns;
        this.seedPercent = seedPercent;
        board = new Board(rows,columns,generateRandomAliveCells());
    }

    private List<Integer> generateRandomAliveCells(){
        int elementsRequired =(int) (rows*columns*(seedPercent)*(0.01));
        List<Integer> indexes =  IntStream.range(0, rows*columns).boxed().collect(Collectors.toList());
        Collections.shuffle(indexes);
        indexes=indexes.subList(0,elementsRequired);
        Collections.sort(indexes);
        return indexes;
    }

    public void start(){
        int generation = 1;
        while(!board.isAllDead()){
            System.out.printf("Generation %d : \n",generation);
            board.show();
            boolean stateChange=board.move();
            if(!stateChange)
                break;
            generation++;
        }

        if(!board.isAllDead())
            System.out.println("This Generation will keep on living");
        else
            System.out.println("All Cell dies...Game ends");
    }

}
