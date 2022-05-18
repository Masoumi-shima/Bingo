import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Board
{
    public List<Square> squares = new ArrayList<>();
    boolean won = false;

    public void addSquares(int row, String line)
    {
        List<String> parts = Arrays.stream(line.split(" ")).filter(s -> !s.isEmpty()).toList();
        for(int i = 0; i < parts.size(); i++)
        {
            Square square = new Square(row, i, parts.get(i));
            squares.add(square);
        }
    }

    public void drawNumber(String drawnNumber)
    {
        Square drawnSquare = squares.stream()
                .filter(square -> square.value.equals(drawnNumber))
                .findFirst()
                .orElse(null);
        if(drawnSquare != null)
        {
            drawnSquare.drawn = true;
            calculateWinningStatus();
        }
    }

    public void calculateWinningStatus()
    {
        won = squares.stream()
                .filter(square -> square.drawn)
                .map(square -> square.row)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .anyMatch(number -> number == 5L) || squares.stream()
                .filter(square -> square.drawn)
                .map(square -> square.column)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .anyMatch(number -> number == 5L);
    }

    public int calculateScore(String drawnNumber)
    {
        int sum = squares.stream().filter(square -> !square.drawn)
                .map(square -> Integer.parseInt(square.value))
                .reduce(0, (a, b) -> a + b);
        return sum * Integer.parseInt(drawnNumber);
    }

    @Override
    public String toString()
    {
        return squares.toString();
    }
}
