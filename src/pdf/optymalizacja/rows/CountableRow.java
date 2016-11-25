package pdf.optymalizacja.rows;

import java.util.ArrayList;
import java.util.List;

import pdf.optymalizacja.Pair;

public class CountableRow {
	public List<CompressedRow> array;

	public CountableRow() {
		array = new ArrayList<CompressedRow>();
	}

	public void add(List<Pair> row, int length) {
		CompressedRow actualRow = isInArray(row);
		if (actualRow == null) {
			array.add(new CompressedRow(row, length));
		}
		else {
			actualRow.count++;
		}
	}

	private CompressedRow isInArray(List<Pair> rows) {
		for (CompressedRow compRow : array) {
			if (areRowsEqual(compRow.rows, rows)) {
				return compRow;
			}
		}

		return null;
	}

	private boolean areRowsEqual(List<Pair> row1, List<Pair> row2) {
		if (row1.size() != row2.size()) {
			return false;
		}
		else {
			int size = row1.size();
			
			for(int i = 0; i < size; i++) {
				if(!row1.get(i).fig.equals(row2.get(i).fig) || row1.get(i).count != row2.get(i).count) {
					return false;
				}
			}
		}

		return true;
	}
}
