import java.util.Scanner;
import java.util.ArrayList;
import java.io;

class Records {
    private String company;
    private int year;
    private int employees_start;
    private int employees_end;
    private int new_hires;
    private int layoffs;
    private int net_change;
    private double hiring_rate_pct;
    private double attrition_rate_pct;
    private double revenue_billion_usd;
    private double stock_price_change_pct;
    private double gdp_growth_us_pct;
    private double unemployment_rate_us_pct;
    private boolean is_estimated;
    private String confidence_level;
    private int data_quality_score;

    public String getCompany(){
        return company;
    }

    public int getYear(){
        return year;
    }

    public int getEmployeesStart(){
        return employees_start;
    }

    public int getEmployeesEnd(){
        return employees_end;
    }

    public int getNewHires(){
        return new_hires;
    }

    public int getLayoffs(){
        return layoffs;
    }

    public int getNetChange(){
        return net_change;
    }

    public double getHiringRatePct(){
        return hiring_rate_pct;
    }

    public double getAttritionRatePct(){
        return attrition_rate_pct;
    }

    public double getRevenueBillionUsd(){
        return revenue_billion_usd;
    }

    public double getStockPriceChangePct(){
        return stock_price_change_pct;
    }

    public double getGdpGrowthUsPct(){
        return gdp_growth_us_pct;
    }

    public double getUnemploymentRateUsPct(){
        return unemployment_rate_us_pct;
    }

    public boolean is_estimated(){
        return is_estimated;
    }

    public String getConfidenceLevel(){
        return confidence_level;
    }

    public int getDataQualityScore(){
        return data_quality_score;
    }
}

public class Project4 {
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        String commandInput;
        
        while (true) {
            commandInput = scnr.next();
            //testing branch
            switch (commandInput) {
                case "LOAD": //read layoffs.csv from current directory
                    ArrayList<Records> records = new ArrayList<>();
                    break;
                case "HELP": //list commands with one-line summaries
                    System.out.println("LOAD | read layoffs.csv from current directory");
                    System.out.println("HELP | lists commands with one-line summaries");
                    System.out.println("SUMMARY [YEAR <yyyy>] | overall totals and average percentages");
                    System.out.println("TOP_LAYOFFS <N> [YEAR <yyyy>] | companies with highest layoffs");
                    System.out.println("TOP_HIRING <N> [YEAR <yyyy>] | companies with highest new_hires");
                    System.out.println("NET_CHANGE [YEAR <yyyy>] | top 5 positive and top 5 negative net_change");
                    System.out.println("COMPANY <name> | a company's rows (all years), sorted by year ASC");
                    System.out.println("STATS [YEAR <yyyy>] | min/avg/max for new_hires, layoffs, net_change");
                    System.out.println("EXPORT <filename> | write the last report to a text file");
                    System.out.println("QUIT | exits");
                    break;
                case "SUMMARY": //overall totals and average percentages
                    break;
                case "TOP_LAYOFFS": //companies with highest layoffs
                    break;
                case "TOP_HIRING": //companies with highest new_hires
                    break;
                case "NET_CHANGE": //top 5 positive and top 5 negative net_change
                    break;
                case "COMPANY": //a company's rows (all years), sorted by year ASC
                    break;
                case "STATS": //min/avg/max for new_hires, layoffs, net_change
                    break;
                case "EXPORT": //write the last report to a text file
                    break;
                case "QUIT": //exit
                    break;
                
            }
        }
    }
}
