import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

class Record {
    private String company;
    private int year;
    private int employees_start;
    private int employees_end;
    private int new_hires;
    private int layoffs;
    private int net_change;
    private double hiring_rate_pct;
    private double attrition_rate_pct;
    private double revenue_billions_usd;
    private double stock_price_change_pct;
    private double gdp_growth_us_pct;
    private double unemployment_rate_us_pct;
    private boolean is_estimated;
    private String confidence_level;
    private int data_quality_score;

    private boolean valid = true;

    public Record (String company, String year, String employees_start, String employees_end, String new_hires, String layoffs, String net_change, String hiring_rate_pct, String attrition_rate_pct, String revenue_billion_usd, String stock_price_change_pct, String gdp_growth_us_pct, String unemployment_rate_us_pct, String is_estimated, String confidence_level, String data_quality_score) {
        try {
            this.company = company.trim();
            this.year = Integer.parseInt(year.trim());
            this.employees_start = Integer.parseInt(employees_start.trim());
            if (this.employees_start < 0) {
                throw new IllegalArgumentException("employees_start, row " + this.company + " " + this.year + " will be skipped: For input string \"" + this.employees_start + "\"");
            }
            this.employees_end = Integer.parseInt(employees_end.trim());
            if (this.employees_end < 0) {
                throw new IllegalArgumentException("employees_end, row " + this.company + " " + this.year + " will be skipped: For input string \"" + this.employees_end + "\"");
            }
            this.new_hires = Integer.parseInt(new_hires.trim());
            if (this.new_hires < 0) {
                throw new IllegalArgumentException("new_hires, row " + this.company + " " + this.year + " will be skipped: For input string \"" + this.new_hires + "\"");
            }
            this.layoffs = Integer.parseInt(layoffs.trim());
            if (this.layoffs < 0) {
                throw new IllegalArgumentException("layoffs, row " + this.company + " " + this.year + " will be skipped: For input string \"" + this.layoffs + "\"");
            }
            this.net_change = Integer.parseInt(net_change.trim());
            this.hiring_rate_pct = Double.parseDouble(hiring_rate_pct.trim());
            this.attrition_rate_pct = Double.parseDouble(attrition_rate_pct.trim());
            this.revenue_billions_usd = Double.parseDouble(revenue_billion_usd.trim());
            this.stock_price_change_pct = Double.parseDouble(stock_price_change_pct.trim());
            this.gdp_growth_us_pct = Double.parseDouble(gdp_growth_us_pct.trim());
            this.unemployment_rate_us_pct = Double.parseDouble(unemployment_rate_us_pct.trim());
            this.is_estimated = Boolean.parseBoolean(is_estimated.trim());
            this.confidence_level = confidence_level.trim();
            this.data_quality_score = Integer.parseInt(data_quality_score.trim());
            loadCount++;
        }
        catch (NumberFormatException e) { //Catches exceptions when parsing numeric values
            System.out.println("Error parsing numeric value from CSV, row " + company + " " + year + " will be skipped: " + e.getMessage());
            this.valid = false;
        }
        catch (IllegalArgumentException e) { //Catches negative values in employees_start, employees_end, new_hires and layoffs
            System.out.println("Negative value in column " + e.getMessage());
            this.valid = false;
        }
    }
    public boolean isValid() {
        return valid;
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

    public double getRevenueBillionsUsd(){
        return revenue_billions_usd;
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

    public boolean getIsEstimated(){
        return is_estimated;
    }

    public String getConfidenceLevel(){
        return confidence_level;
    }

    public int getDataQualityScore(){
        return data_quality_score;
    }
}


class Project4 {
    public ArrayList<Record> getYearList(ArrayList<Record> records, int year) {
    ArrayList<Record> byYear = new ArrayList<Record>();
    for (Record r : records) {
        if (r.getYear() == year) {
            byYear.add(r);
        }
    }
    return byYear;
    }
    public ArrayList<Record> getCompanyList(ArrayList<Record> records, String company) {
        ArrayList<Record> byCompany = new ArrayList<Record>();
        for (Record r : records) {
            if (r.getCompany().equals(company)) {
                byCompany.add(r);
            }
        }
        return byCompany;
    }
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        String commandInput = "";
        String yearInput;
        String lastReport = "";
        int skipped = 0;
        ArrayList<Record> records = new ArrayList<>();
        
        while (commandInput != "QUIT") {
            String line = scnr.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ", 2);
            commandInput = parts[0].toUpperCase();
            if (parts.length > 1) yearInput = parts[1];
            else yearInput = "";
            //testing branch
            switch (commandInput) {
                case "LOAD": //read WorkforceData.csv from current directory
                    try (BufferedReader br = new BufferedReader(new FileReader("WorkforceData.csv"))) {
                        String line1 = br.readLine();
                        while ((line1 = br.readLine()) != null) {
                            String[] fields = line1.split(",");
                            Record rec = new Record(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8],fields[9],fields[10],fields[11],fields[12],fields[13],fields[14],fields[15]);
                            if (rec.isValid()) records.add(rec);
                            else skipped++;
                        }
                        System.out.println("File loaded successfully.");
                    } 
                    catch (IOException e) {
                        System.out.println("Error reading CSV: " + e.getMessage());
                    }
                    break;
                case "HELP": //list commands with one-line summaries
                    System.out.println("LOAD | read WorkforceData.csv from current directory");
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
                case "SUMMARY": { //overall totals and average percentages(anusha)
                    ArrayList<Record> data = records;
                     String scope = "All Years";
                    if (commandInput.contains("YEAR")) {
                    yearInput = scnr.next(); 
                    try {
                    int yr = Integer.parseInt(yearInput.trim());
                    data = new ArrayList<>();
                    for (Record r : records)
                    if (r.getYear() == yr) data.add(r);
                    scope = "Year " + yr;
                    }  catch (NumberFormatException e) {
                    System.out.println("Invalid year: " + yearInput);
         
        
                    System.out.println("The number of rows loaded: " + loadCount);
                    System.out.println("The number of rows skipped: " + skipCount);
                    break;
                case "TOP_LAYOFFS": //companies with highest layoffs
                    break;
                case "TOP_HIRING": //companies with highest new_hires
                    break;
                 }
                }
                if (data.isEmpty()) { 
                 System.out.println("No records found for: " + scope);
                break;
                 }
                 long totalHires = 0, totalLayoffs = 0, totalNetChange = 0;
                 long totalStart = 0, totalEnd = 0;
                double sumHiringRate = 0, sumAttrition = 0, sumRevenue = 0;
                 for (Record r : data) {
                    totalHires     += r.getNewHires();
                    totalLayoffs   += r.getLayoffs();
                    totalNetChange += r.getNetChange();
                    totalStart     += r.getEmployeesStart();
                    totalEnd       += r.getEmployeesEnd();
                    sumHiringRate  += r.getHiringRatePct();
                    sumAttrition   += r.getAttritionRatePct();
                    sumRevenue     += r.getRevenueBillionsUsd(); 
                }
                int n = data.size();
                lastReport = String.format("%-35s %s%n",     "=== SUMMARY ===", scope)
                            + String.format("%-35s %d%n",     "Rows loaded (total):",   records.size())
                            + String.format("%-35s %d%n",     "Rows skipped (errors):", skipped)
                            + String.format("%-35s %d%n",     "Rows in this report:",   n)
                            + String.format("%-35s %,d%n",    "Total New Hires:",       totalHires)
                            + String.format("%-35s %,d%n",    "Total Layoffs:",         totalLayoffs)
                            + String.format("%-35s %,d%n",    "Total Net Change:",      totalNetChange)
                            + String.format("%-35s %,d%n",    "Total Employees (start)",totalStart)
                            + String.format("%-35s %,d%n",    "Total Employees (end):", totalEnd)
                            + String.format("%-35s %.2f%%%n", "Avg Hiring Rate:",       sumHiringRate / n)
                            + String.format("%-35s %.2f%%%n", "Avg Attrition Rate:",    sumAttrition  / n)
                            + String.format("%-35s $%.2fB%n", "Avg Revenue (USD):",     sumRevenue    / n);
                 System.out.println(lastReport);
                break;
                }
                case "TOP_LAYOFFS": {//companies with highest layoffs
                    int topN;
                    try { topN = Integer.parseInt(scnr.next().trim()); }
                    catch (NumberFormatException e) { System.out.println("Usage: TOP_LAYOFFS <N> [YEAR <yyyy>]"); break; }

                ArrayList<Record> data = new ArrayList<>(records);
                String scope = "All Years";
                String peek = scnr.hasNext() ? scnr.next() : "";
                if (peek.equalsIgnoreCase("YEAR")) {
                    try {
                        int yr = Integer.parseInt(scnr.next().trim());
                        data = new ArrayList<>();
                        for (Record r : records)
                            if (r.getYear() == yr) data.add(r);
                        scope = "Year " + yr;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year."); break;
                    }
                 }
                if (data.isEmpty()) { System.out.println("No records found for: " + scope); break; }
                data.sort((a, b) -> Integer.compare(b.getLayoffs(), a.getLayoffs()));
                int limit = Math.min(topN, data.size());
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("=== TOP %d LAYOFFS === %s%n", topN, scope));
                sb.append(String.format("%-26s %6s %10s%n", "Company", "Year", "Layoffs"));
                sb.append("-".repeat(46)).append("\n");
                for (int i = 0; i < limit; i++) {
                    Record r = data.get(i);
                    sb.append(String.format("%-26s %6d %10d%n", r.getCompany(), r.getYear(), r.getLayoffs()));
                }
                lastReport = sb.toString();
                System.out.println(lastReport);
                break;
                }
                case "TOP_HIRING": {//companies with highest new_hires
                int topN;
                try { topN = Integer.parseInt(scnr.next().trim()); }
                catch (NumberFormatException e) { System.out.println("Usage: TOP_HIRING <N> [YEAR <yyyy>]"); break; }

                ArrayList<Record> data = new ArrayList<>(records);
                String scope = "All Years";
                String peek = scnr.hasNext() ? scnr.next() : "";
                if (peek.equalsIgnoreCase("YEAR")) {
                    try {
                        int yr = Integer.parseInt(scnr.next().trim());
                        data = new ArrayList<>();
                        for (Record r : records)
                            if (r.getYear() == yr) data.add(r);
                        scope = "Year " + yr;
                    } catch (NumberFormatException e) {
                    System.out.println("Invalid year."); break;
                    }
                }
                if (data.isEmpty()) { System.out.println("No records found for: " + scope); break; }
                data.sort((a, b) -> Integer.compare(b.getNewHires(), a.getNewHires()));
                int limit = Math.min(topN, data.size());
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("=== TOP %d HIRING === %s%n", topN, scope));
                sb.append(String.format("%-26s %6s %10s%n", "Company", "Year", "New Hires"));
                sb.append("-".repeat(46)).append("\n");
                for (int i = 0; i < limit; i++) {
                    Record r = data.get(i);
                    sb.append(String.format("%-26s %6d %10d%n", r.getCompany(), r.getYear(), r.getNewHires()));
                }
                lastReport = sb.toString();
                System.out.println(lastReport);
                break;
            }
                case "NET_CHANGE": { //top 5 positive and top 5 negative net_change
                ArrayList<Record> data = new ArrayList<>(records);
                String scope = "All Years";
                if (commandInput.contains("YEAR")) {
                    yearInput = scnr.next();
                    try {
                        int yr = Integer.parseInt(yearInput.trim());
                        data = new ArrayList<>();
                        for (Record r : records)
                            if (r.getYear() == yr) data.add(r);
                            scope = "Year " + yr;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid year: " + yearInput); break;
                        }
                }
                if (data.isEmpty()) { System.out.println("No records found for: " + scope); break; }
                data.sort((a, b) -> Integer.compare(b.getNetChange(), a.getNetChange()));

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("=== NET CHANGE === %s%n", scope));
                sb.append(String.format("%-26s %6s %12s%n", "Company", "Year", "Net Change"));
                sb.append("-".repeat(48)).append("\n");
                sb.append("  Top 5 Growth:\n");
                int count = 0;
                for (Record r : data) {
                    if (count >= 5 || r.getNetChange() <= 0) break;
                    sb.append(String.format("  %-26s %6d %+12d%n", r.getCompany(), r.getYear(), r.getNetChange()));
                    count++;
                }
                if (count == 0) sb.append("  (none)\n");
                sb.append("  Top 5 Decline:\n");
                count = 0;
                for (int i = data.size() - 1; i >= 0 && count < 5; i--) {
                    Record r = data.get(i);
                    if (r.getNetChange() >= 0) break;
                    sb.append(String.format("  %-26s %6d %+12d%n", r.getCompany(), r.getYear(), r.getNetChange()));
                    count++;
                }
                if (count == 0) sb.append("  (none)\n");
                lastReport = sb.toString();
                System.out.println(lastReport);
                break;
                }
                case "COMPANY": { //a company's rows (all years), sorted by year ASC
                String name = scnr.nextLine().trim().replaceAll("^\"|\"$", "");
                if (name.isEmpty()) { System.out.println("Usage: COMPANY <name>"); break; }
                String target = name.toLowerCase();
                ArrayList<Record> exact = new ArrayList<>();
                ArrayList<Record> partial = new ArrayList<>();
                for (Record r : records) {
                    String co = r.getCompany().toLowerCase();
                    if (co.equals(target))        exact.add(r);
                else if (co.contains(target)) partial.add(r);
                }
                exact.sort((a, b) -> Integer.compare(a.getYear(), b.getYear()));
                partial.sort((a, b) -> Integer.compare(a.getYear(), b.getYear()));
                exact.addAll(partial);
                if (exact.isEmpty()) { System.out.println("No records found for: " + name); break; }

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("=== COMPANY: %s ===%n", name.toUpperCase()));
                sb.append(String.format("%-26s %6s %8s %8s %8s %8s %10s%n",
        "Company", "Year", "Hires", "Layoffs", "Start", "End", "Net"));
                sb.append("-".repeat(84)).append("\n");
                for (Record r : exact) {
                    sb.append(String.format("%-26s %6d %8d %8d %8d %8d %10d%n",
                        r.getCompany(), r.getYear(),
                        r.getNewHires(), r.getLayoffs(),
                        r.getEmployeesStart(), r.getEmployeesEnd(),
                        r.getNetChange()));
                }
                lastReport = sb.toString();
                System.out.println(lastReport);
                break;
                } //a company's rows (all years), sorted by year ASC
                    
                case "STATS": { //min/avg/max for new_hires, layoffs, net_change
                ArrayList<Record> data = records;
                String scope = "All Years";
                if (commandInput.contains("YEAR")) {
                    yearInput = scnr.next();
                    try {
                        int yr = Integer.parseInt(yearInput.trim());
                        data = new ArrayList<>();
                        for (Record r : records)
                            if (r.getYear() == yr) data.add(r);
                        scope = "Year " + yr;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year: " + yearInput); break;
                    }
                }
                if (data.isEmpty()) { System.out.println("No records found for: " + scope); break; }

                int minHires = Integer.MAX_VALUE, maxHires = Integer.MIN_VALUE;
                int minLayoffs = Integer.MAX_VALUE, maxLayoffs = Integer.MIN_VALUE;
                int minNet = Integer.MAX_VALUE, maxNet = Integer.MIN_VALUE;
                long sumHires = 0, sumLayoffs = 0, sumNet = 0;
                for (Record r : data) {
                    int h = r.getNewHires(), l = r.getLayoffs(), nc = r.getNetChange();
                    if (h < minHires)   minHires   = h;
                    if (h > maxHires)   maxHires   = h;
                    if (l < minLayoffs) minLayoffs = l;
                    if (l > maxLayoffs) maxLayoffs = l;
                    if (nc < minNet)    minNet     = nc;
                    if (nc > maxNet)    maxNet     = nc;
                    sumHires   += h;
                    sumLayoffs += l;
                    sumNet     += nc;
                }
                int cnt = data.size();
                lastReport = String.format("=== STATS === %s%n", scope)
                            + String.format("%-15s %10s %10s %10s%n", "Metric", "Min", "Avg", "Max")
                            + "-".repeat(49) + "\n"
                            + String.format("%-15s %10d %10.1f %10d%n", "New Hires",  minHires,   (double)sumHires   / cnt, maxHires)
                            + String.format("%-15s %10d %10.1f %10d%n", "Layoffs",    minLayoffs, (double)sumLayoffs / cnt, maxLayoffs)
                            + String.format("%-15s %10d %10.1f %10d%n", "Net Change", minNet,     (double)sumNet     / cnt, maxNet);
                System.out.println(lastReport);
                break;
                }
                    
                case "EXPORT": { //write the last report to a text file
                    String filename = scnr.next().trim().replaceAll("^\"|\"$", "");
                        if (lastReport.isEmpty()) { System.out.println("No report to export yet."); break; }
                        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                                new FileOutputStream(filename), "UTF-8"))) {
                        pw.println(lastReport);
                        System.out.println("Report exported to: " + filename);
                    } catch (IOException e) {
                        System.out.println("Error writing file: " + e.getMessage());
                    }
                    break;
                    }
                    
                case "QUIT": //exit
                    System.out.println("Goodbye!");
                    scnr.close();
                    System.exit(0);
                    break;
                
            }
        }
    }
}
