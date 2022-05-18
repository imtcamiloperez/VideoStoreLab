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
        rentals.add(arg);
    }

    private String header() {
        return String.format("Rental record for %s \n ", customerName);
    }

    double thisAmount = 0;

    // determine amount for each line
            switch(rental.getMovie().

    getPriceCode())

    {
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
            if((rental.getMovie().

    getPriceCode() ==Movie.NEW_RELEASE)&&
            rental.getDaysRented()>1)frequentRenterPoints ++;

    //show figures for this rental
    statementText +="\t"+rental.getMovie().

    getTitle()+"\t"+
            String.valueOf(thisAmount)+"\n";
    totalAmount +=thisAmount;

    //add footer lines
    statementTxt +="You owed "+String.valueOf(totalAmount)+"\n";
    statementTxt +="You earned "+String.valueOf(frequentRenterPoints)+
            " frequent renter points\n";
        return statementTxt;


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

