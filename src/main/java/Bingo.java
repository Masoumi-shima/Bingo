import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bingo
{
    private static final String FILE_PATH = "/Users/melpomene/Downloads/puzzle.txt";

    public static void main(String[] args)
    {
        List<String> drawnNumbers = new ArrayList<>();
        List<Board> boards = getBingoData(drawnNumbers);
        int boardsSize = boards.size();

        for(String drawnNumber : drawnNumbers)
        {
            for(Board board : boards)
            {
                board.drawNumber(drawnNumber);
                if(board.won && (boards.size() == 1 || boards.size() == boardsSize))
                {
                    System.out.println(board.calculateScore(drawnNumber));
                }
            }
            boards = boards.stream().filter(board -> !board.won).toList();
        }
    }

    private static List<Board> getBingoData(List<String> drawnNumbers)
    {
        List<Board> boards = new ArrayList<>();
        try
        {
            File myObj = new File(FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            drawnNumbers.addAll(Arrays.asList(myReader.nextLine().split(",")));
            Board board = null;
            int row = 0;
            while (myReader.hasNextLine())
            {
                String currentLine = myReader.nextLine();
                if(currentLine.isEmpty())
                {
                    if(board != null)
                    {
                        boards.add(board);
                    }
                    row = 0;
                    board = new Board();
                }
                else
                {
                    if(board != null)
                    {
                        board.addSquares(row++, currentLine);
                    }
                }
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return boards;
    }
}
