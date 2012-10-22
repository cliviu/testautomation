package testingapp;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MainTable implements SelectionListener {

	private final static String[] HEADERS = { "   Test name", "Outcome", "Report" };
	private final static int[] WIDTHS = { 60, 29, 11 };
	private final TableViewer _tableViewer;
	private final TestTableComparator _comparator;
	private Test[] testMocks = new Test[] {
			new Test("Filter ads by suburb", "Pass", ""),
			new Test("Refine search results by price range", "Pass", ""),
			new Test("Search for items using keyword", "Pass", "") };

	public MainTable(Group group) {
		_tableViewer = new TableViewer(group, SWT.BORDER | SWT.FULL_SELECTION  | SWT.CHECK | SWT.V_SCROLL);
		fillTableViewer(_tableViewer);
		_tableViewer.setInput(testMocks);
		 _comparator = new TestTableComparator();
		Table table = _tableViewer.getTable();
		
		//table.setLinesVisible(true);_loggerBackend
		TableItem[] items = table.getItems();
		for (TableItem item : items) {
			System.out.println("Items  " + items);
			TableEditor editor = new TableEditor(table);
			Button button = new Button(table, SWT.PUSH);
			button.setText("View report");			
			button.pack();
			editor.minimumWidth = button.getSize().x;
			editor.horizontalAlignment = SWT.LEFT;
			editor.setEditor(button, item, 2);
		}
		_tableViewer.getTable().addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		          if (event.detail == SWT.CHECK) {
		            System.out.println("You checked " + event.item);
		          } else {
		        	System.out.println("You selected " + event.item);
		          }
		        }
		      });
		
        _comparator.setColumn(0);
        _tableViewer.setComparator(_comparator);
		table.setSortColumn(table.getColumn(0));
        table.setSortDirection(SWT.DOWN);
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column,
		      final int index) {
		    SelectionAdapter selectionAdapter = new SelectionAdapter() {
		      @Override
		      public void widgetSelected(SelectionEvent e) {
		        _comparator.setColumn(index);
		        int dir = _comparator.getDirection();
		        _tableViewer.getTable().setSortDirection(dir);
		        _tableViewer.getTable().setSortColumn(column);
		        _tableViewer.refresh();
		      }
		    };
		    return selectionAdapter;
		  }
	public TableViewer getTableViewer() {
		return _tableViewer;
	}

	private void fillTableViewer(TableViewer tableViewer) {

		TableLayout tableLayout = new TableLayout();
		for (int width : WIDTHS) {
			ColumnWeightData columnWeightData = new ColumnWeightData(width,
					true);
			columnWeightData.resizable=true;
			tableLayout.addColumnData(columnWeightData);
		}
		Table table = tableViewer.getTable();
		table.addSelectionListener(this);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		int colNumber = 0;
		for (String header : HEADERS) {
			TableColumn column = new TableColumn(table, SWT.LEFT);			
			column.setText(header);
			column.addSelectionListener(
		            getSelectionAdapter( column, colNumber));
			colNumber++;

		}

		table.setLayout(tableLayout);
		//table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tableViewer.setContentProvider(new TestResultsTableContentProvider());
		tableViewer.setLabelProvider(new TestResultsTableLabelProvider());
	}
	
	private class TestResultsTableContentProvider implements
			IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Test[]) {
				return (Test[]) inputElement;
			} else {
				return null;
			}
		}
	}

	/**
	 * public Color getForeground(Object element, int columnIndex) { float ratio
	 * = ((Share)element).getRatio(); if (ratio < 0.4) { return
	 * Display.getCurrent().getSystemColor(SWT.COLOR_GREEN); } else if (ratio <
	 * 0.9) { return null; } else { return
	 * Display.getCurrent().getSystemColor(SWT.COLOR_RED); } }
	 * 
	 * @author carausu
	 * 
	 */

	private class TestResultsTableLabelProvider implements ITableLabelProvider,
			ITableColorProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {

			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof Test) {
				Test test = (Test) element;
				switch (columnIndex) {
				case 0:
					return test.getName();
				case 1:
					return test.getOutcome();
				case 2:
					return test.getReport();
				}
			}
			return "";
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			//return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
			return null;
		}

	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		//System.out.println("Widget selected " + e);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
