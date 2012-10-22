package testingapp;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

public class MainWindow extends ApplicationWindow {
	private Table table;

	/**
	 * Create the application window.
	 */
	public MainWindow() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("04Jun12 10:05");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginWidth = 10;
		composite.setLayout(gl_composite);
		
		Group groupSelectTests = new Group(composite, SWT.NONE);
		groupSelectTests.setLayout(new GridLayout(6, false));
		groupSelectTests.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblSelectTests = new Label(groupSelectTests, SWT.NONE);
		lblSelectTests.setText("Select tests:");
		
		ComboViewer comboViewer = new ComboViewer(groupSelectTests, SWT.NONE);
		Combo combo = comboViewer.getCombo();
		combo.add("All");
		combo.add("Selected");
		
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.select(0);
		
		Label lblFilterByName = new Label(groupSelectTests, SWT.NONE);
		lblFilterByName.setText("Filter by name :");
		
		Text filterByName = new Text(groupSelectTests, SWT.NONE);
		filterByName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(groupSelectTests, SWT.NONE);
		lblNewLabel.setText("OR");
		
		Combo comboOr = new Combo(groupSelectTests, SWT.NONE);
		comboOr.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboOr.add("Filter by tag");
		comboOr.add("Filter by ...");
		comboOr.select(0);
		
		Group group = new Group(composite, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group.setLayout(new GridLayout(1, false));
		
		TableViewer tableViewer = new MainTable(group).getTableViewer();
		table = tableViewer.getTable();
		GridData tableGridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		tableGridData.grabExcessHorizontalSpace = true;
		tableGridData.grabExcessVerticalSpace = true;
		table.setLayoutData(tableGridData);
		
		
		Group runTestsGroup = new Group(composite, SWT.NONE);
		GridLayout gl_runTestsGroup = new GridLayout(5, false);
		gl_runTestsGroup.horizontalSpacing = 20;
		gl_runTestsGroup.marginRight = 15;
		runTestsGroup.setLayout(gl_runTestsGroup);
		runTestsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(runTestsGroup, SWT.NONE);
		lblNewLabel_1.setText("Run tests");
		
		Label lblChooseEnvironment = new Label(runTestsGroup, SWT.NONE);
		lblChooseEnvironment.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		lblChooseEnvironment.setText("Choose environment:");
		
		ComboViewer comboViewer_1 = new ComboViewer(runTestsGroup, SWT.NONE);
		Combo comboEnvironment = comboViewer_1.getCombo();
		comboEnvironment.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		comboEnvironment.add("UAT");
		comboEnvironment.add("Environment x");
		comboEnvironment.select(0);
		new Label(runTestsGroup, SWT.NONE);
		
		Button btnStop = new Button(runTestsGroup, SWT.NONE);
		GridData gd_btnStop = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnStop.widthHint = 75;
		btnStop.setLayoutData(gd_btnStop);
		btnStop.setText("Stop");
		
		Group progressGroup = new Group(composite, SWT.NONE);
		GridLayout gl_progressGroup = new GridLayout(4, false);
		gl_progressGroup.horizontalSpacing = 20;
		gl_progressGroup.marginRight = 15;
		progressGroup.setLayout(gl_progressGroup);
		progressGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(progressGroup, SWT.NONE);
		lblNewLabel_2.setText("Progress");
		new Label(progressGroup, SWT.NONE);
		
		ProgressBar progressBar = new ProgressBar(progressGroup, SWT.NONE);
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		progressBar.setSelection(50);
		new Label(progressGroup, SWT.NONE);
		new Label(progressGroup, SWT.NONE);
		new Label(progressGroup, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(progressGroup, SWT.NONE);
		lblNewLabel_3.setText("Currently Running : Sort ads high to low price");
		
		Label lblPass = new Label(progressGroup, SWT.NONE);
		lblPass.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		lblPass.setText("Pass: 2 Fail :1");
	

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			MainWindow window = new MainWindow();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Test Runner");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(640, 480);
	}
}
