import junit.framework.*;
import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {
	private Statement statement;
	private final double DELTA = .001;
	private Movie newRealeaseMovie1;
	private Movie newRealeaseMovie2;
	private Movie newChildrenMovie;
	private Movie regular1;
	private Movie regular2;
	private Movie regular3;

	@Before
	protected void setUp() {
		statement = new Statement("Customer");
		newRealeaseMovie1 = new Movie("New Release 1",Movie.NEW_RELEASE);
		newRealeaseMovie2 = new Movie("New Release 2",Movie.NEW_RELEASE);
		newChildrenMovie = new Movie("New Release Child",Movie.CHILDRENS);
		regular1 = new Movie("regular 1", Movie.REGULAR);
		regular2 = new Movie("regular 2", Movie.REGULAR);
		regular3 = new Movie("regular 3", Movie.REGULAR);
	}

	@Test
	public void testSingleNewReleaseStatementTotals() {
		statement.addRental(new Rental(newChildrenMovie1, 3));
		statement.generate();
		assertEquals(9.0, statement.getTotal());
		assertEquals(2, statement.getFrequentRenterPoints());
		
	}
	@Test
	public void testDualNewReleaseStatementTotals() {
		statement.addRental(new Rental(newRealeaseMovie1, 3));
		statement.addRental(new Rental(newRealeaseMovie2, 3));
		statement.generate();
		assertEquals(18.0, statement.getTotal(), DELTA);
		assertEquals(4, statement.getFrequentRenterPoints());

	}

	@Test
	public void testSingleChildrensStatementTotals() {
		statement.addRental(new Rental(newChildrenMovie, 3));
		statement.generate();
		assertEquals(1.5, statement.getTotal(), DELTA);
		assertEquals(1, statement.getFrequentRenterPoints());
	}
	@Test
	public void testMultipleRegularStatementTotals() {
		statement.addRental(new Rental(regular1, 1));
		statement.addRental(new Rental(regular2, 2));
		statement.addRental(new Rental(regular3, 3));
		statement.generate();
		assertEquals(7.5, statement.getTotal(), DELTA);
		assertEquals(3, statement.getFrequentRenterPoints());

	}
	@Test
	public void testMultipleRegularStatementFormat() {
		statement.addRental(new Rental(regular1, 1));
		statement.addRental(new Rental(regular2, 2));
		statement.addRental(new Rental(regular3, 3));

		assertEquals(
				"Rental Record for Customer\n\tregular 1\t2.0\n\tregular 2\t2.0"
						+ "\n\tregular 3\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n",
				statement.generate());
	}
}
