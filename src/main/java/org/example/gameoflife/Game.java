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
        board = new Board(rows,columns);
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
        try {
            board.initialGeneration(generateRandomAliveCells());
            int generation = 0;
            while (!board.isGenerationEnds()) {
                System.out.printf("Generation %d : \n", generation);
                board.display();
                boolean genChange = board.nextGeneration();
                if (!genChange){
                    System.out.println("This Generation will keep on living");
                    return;
                }
                generation++;
            }
            System.out.printf("Generation %d : \n", generation);
            board.display();
            System.out.println("All Cell dies...Game ends");

        }catch (Exception e){
            System.out.println("Initial Generation is all dead");
        }
    }

}
