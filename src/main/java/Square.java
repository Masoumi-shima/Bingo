public class Square
{
    public int row;
    public int column;
    public String value;
    public boolean drawn;

    public Square(int row, int column, String value)
    {
        this(row, column, value, false);
    }

    public Square(int row, int column, String value, boolean drawn)
    {
        this.row = row;
        this.column = column;
        this.value = value;
        this.drawn = drawn;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
