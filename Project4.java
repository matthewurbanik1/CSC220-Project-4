import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

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

    public Records (String company, String year, String employees_start, String employees_end, String new_hires, String layoffs, String net_change, String hiring_rate_pct, String attrition_rate_pct, String revenue_billion_usd, String stock_price_change_pct, String gdp_growth_us_pct, String unemployment_rate_us_pct, String is_estimated, String confidence_level, String data_quality_score) {
        try {
            this.company = company;
            this.year = Integer.parseInt(year);
            this.employees_start = Integer.parseInt(employees_start);
            if (this.employees_start < 0) {
                throw new IllegalArgumentException("employees_start");
            }
            this.employees_end = Integer.parseInt(employees_end);
            if (this.employees_end < 0) {
                throw new IllegalArgumentException("employees_end");
            }
            this.new_hires = Integer.parseInt(new_hires);
            if (this.new_hires < 0) {
                throw new IllegalArgumentException("new_hires");
            }
            this.layoffs = Integer.parseInt(layoffs);
            if (this.layoffs < 0) {
                throw new IllegalArgumentException("layoffs");
            }
            this.net_change = Integer.parseInt(net_change);
            this.hiring_rate_pct = Double.parseDouble(hiring_rate_pct);
            this.attrition_rate_pct = Double.parseDouble(attrition_rate_pct);
            this.revenue_billion_usd = Double.parseDouble(revenue_billion_usd);
            this.stock_price_change_pct = Double.parseDouble(stock_price_change_pct);
            this.gdp_growth_us_pct = Double.parseDouble(gdp_growth_us_pct);
            this.unemployment_rate_us_pct = Double.parseDouble(unemployment_rate_us_pct);
            this.is_estimated = Boolean.parseBoolean(is_estimated);
            this.confidence_level = confidence_level;
            this.data_quality_score = Integer.parseInt(data_quality_score);
        }
        catch (NumberFormatException e) { //Catches exceptions when parsing numeric values
            System.out.println("Error parsing numeric value from CSV, row " + company + " " + year + " will be skipped: " + e.getMessage());
        }
        catch (IllegalArgumentException e) { //Catches negative values in employees_start, employees_end, new_hires and layoffs
            System.out.println("Negative value in column " + e.getMessage() + ", row " + company + " " + year + " will be skipped");
        }
    }
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
        String commandInput = "";
        String yearInput;
        
        while (commandInput != "QUIT") {
            commandInput = scnr.next();
            //testing branch
            switch (commandInput) {
                case "LOAD": //read layoffs.csv from current directory
                    ArrayList<Records> records = new ArrayList<>();
                    try (BufferedReader br = new BufferedReader(new FileReader("WorkforceData.csv"))) {
                        String line = br.readLine();
                        while ((line = br.readLine()) != null) {
                            String[] fields = line.split(",");
                            records.add(new Records(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8],fields[9],fields[10],fields[11],fields[12],fields[13],fields[14],fields[15]));
                        }
                        System.out.println("File loaded successfully.");
                    } 
                    catch (IOException e) {
                        System.out.println("Error reading CSV: " + e.getMessage());
                    }
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
                    //TODO: Rubric says to print loaded vs skipped rows
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
