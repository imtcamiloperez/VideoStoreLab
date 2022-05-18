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
        initialize();
        String Statement = header();
        for (Rental rental : rentals) {
            double thisAmount = 0;

            // determine amount for each line
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2)
                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3)
                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
                    break;
            }

            // add frequent renter points
            frequentRenterPoints ++;
            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    rental.getDaysRented() > 1) frequentRenterPoints ++;

            //show figures for this rental
            result += "\t" + rental.getMovie().getTitle()+ "\t" +
                    String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        statementTxt += "You owed " + String.valueOf(totalAmount) + "\n";
        statementTxt += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points\n";
        return statementTxt;
    }

    private String header(){
        return String.format("Rental record for %s \n ", customerName);
    }

    private void initialize(){
        totalAmount = 0;
        frequentRenterPoints = 0;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
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
}
