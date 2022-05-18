import java.util.ArrayList;
import java.util.List;


public class Statement {
    private String customerName;
    private List<Rental> rentals = new ArrayList<Rental>();
    private int frequentRenterPoints;
    private double totalAmount;

    public Statement(String customerName) {
        this.customerName = customerName;
    }

    public String generate() {
        clearTotals();
        String statementText = header();
        statementText += rentalLines();
        //add footer lines
        statementText += footer();
        return statementText;
    }

    private void clearTotals() {
        totalAmount = 0;
        frequentRenterPoints = 0;
    }

    public void rentalLines(Rental arg) {
        String rentalLines ="";
        for (Rental rental : rentals)
            rentalLines += rentalLine(rental);
        return rentalLines;
    }

    private String header() {
        return String.format("Rental record for %s \n ", customerName);
    }

    double thisAmount = 0;

    private rentalLine(Rental rental){
        double rentalAmount = determineAmount(rental);
        frequentRenterPoints = determineFrequentRenterPoints(rental);
        totalAmount += rentalAmount;

        return formatRentalLine(rental, rentalAmount);


    }

    private String formatRentalLine(Rental rental, double rentalAmount){
        return String.format("\t%s\t%.1f\n", rental.getMovie().getTitle(),rentalAmount);
    }

    private int  determineFrequentRenterPoints(Rental rental){
        boolean bonusIsEarned = rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1;
        if (bonusIsEarned)
            return 2;
        return 1;
    }
    private double determineAmount(Rental rental) {
        double rentalAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                rentalAmount += 2;
                if (rental.getDaysRented() > 2)
                    rentalAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                rentalAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                rentalAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    rentalAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }

    public String getCustomerName() {
        return customerName;
    }

	public double getTotal() {
		// TODO Auto-generated method stub
		return totalAmount;
	}

	public int getFrequentRenterPoints() {
		// TODO Auto-generated method stub
		return frequentRenterPoints;
	}
	private String footer(){
    return String.format("\"You owed %.1f\\n\"\n" +
            "\t\t\t\t+ \"You earned %d frequent renter points\\n\",\n" +
            "\t\t\t\ttotalAmount, frequentRenterPoints);
}
}

