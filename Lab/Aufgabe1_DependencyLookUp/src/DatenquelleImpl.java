import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class DatenquelleImpl implements IDatenquelle {

	private int minValue = -30;
	private int maxValue = +30;
	private int nrOfPoints = 30;

	@Override
	public ArrayList<Point> getDatenreihe() {
		// generate some points

		ArrayList<Point> listOfPoints = new ArrayList<>(nrOfPoints);
		for (int i = 0; i <= nrOfPoints - 1; i++) {
			listOfPoints.add(new Point(i, randomInt()));
		}

		return listOfPoints;
	}

	/**
	 * @return a random integer value from the interval [minValue, maxValue]
	 */
	private int randomInt() {
		if (minValue > maxValue) {
			throw new IllegalStateException(
					"minValue must not be bigger than maxValue");
		}

		return (int) (new Random().nextFloat() * (maxValue - minValue) + minValue);
	}
}
