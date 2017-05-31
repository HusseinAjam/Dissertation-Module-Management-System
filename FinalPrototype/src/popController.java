import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;

import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class popController {

	
	@FXML
	private SpreadsheetView spreadsheet;
	
	private GridBase grid;
	private ObservableList<ObservableList<SpreadsheetCell>> rows;
	private ObservableList<SpreadsheetCell> list;
	
	public void initialize() throws IOException {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(chooser);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
         }
        
        ReadExcelFile readObject = new ReadExcelFile();
		ArrayList<List<String>> main = readObject.reading(chooser.getSelectedFile().getPath());
		Iterator<List<String>> rowsListIterator = main.iterator();
		Iterator<String> columnsListIterator = null;
	    int rowCount = readObject.getRowNumber();
	    int columnCount = 20;

		 grid = new GridBase(rowCount, columnCount);
	     rows = FXCollections.observableArrayList();
	    for (int row = 0; row < grid.getRowCount(); ++row) {
	    	if(rowsListIterator.hasNext())
	    	columnsListIterator = rowsListIterator.next().iterator();
	      
	        list = FXCollections.observableArrayList();
		        for (int column = 0; column < grid.getColumnCount(); ++column) 
	        {
	        	if(columnsListIterator.hasNext())
 	            list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1,columnsListIterator.next()));
	        	else
 	            list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1," "));	
	        }
	        rows.add(list);
		    }
	    grid.setRows(rows);
 	    try {
			spreadsheet.setGrid(grid);
		} catch (Exception e) {
 			e.printStackTrace();
		}
    }
	
}
