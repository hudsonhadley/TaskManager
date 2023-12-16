public class Date
{
    private int day;
    private int month;
    private int year;

    public Date()
    {
        day = 1;
        month = 1;
        year = 2000;
    }

    public Date(int day, int month, int year) throws Exception
    {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day) throws Exception
    {
        if (day < 1 || day > 31)
        {
            throw new Exception("Date Error: Day must be between 1 and 31");
        }

        this.day = day;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month) throws Exception
    {
        if (month < 1 || month > 12)
        {
            throw new Exception("Date Error: Month must be between 1 and 12");
        }

        this.month = month;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year) throws Exception
    {
        if (year < 0)
        {
            throw new Exception("Date Error: Year must be non-negative");
        }


        this.year = year;
    }


    public String toString()
    {
        String dateString = "";

        if (this.month < 10)
        {
            dateString += "0";
        }
        dateString += this.month + "/";

        if (this.day < 10)
        {
            dateString += "0";
        }
        dateString += this.day + "/";

        String yearString = Integer.toString(this.year);
        while (yearString.length() < 4)
        {
            yearString = "0" + yearString;
        }

        return dateString + yearString;
    }


    public boolean lessThan(Date other)
    {
        if (this.year < other.year)
        {
            return true;
        } else if (this.year > other.year)
        {
            return false;
        } else
        {
            if (this.month < other.month)
            {
                return true;
            } else if (this.month > other.month)
            {
                return false;
            } else
            {
                return this.day < other.day;
            }
        }
    }
}
