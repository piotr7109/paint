package pdf.optymalizacja.rows;

import java.util.List;

import pdf.optymalizacja.Pair;

public class CompressedRow {
	public int count;
	public int length;
	public List<Pair> rows;
	
	public CompressedRow(List<Pair> row, int length) {
		count = 1;
		rows = row;
		this.length = length;
	}
	
	public void add(Pair row) {
		count++;
	}
}
