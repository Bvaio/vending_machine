package com.techelevator;

import com.techelevator.view.Menu;

public class CaTEringCapstoneCLI {
	private Menu menu;
//	private boolean isRunning = true;

	public CaTEringCapstoneCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI(menu);
		cli.run();
	}

	public void run() {

//		while (isRunning) {
//			//  to do -- build out main menu
//			menu.showMenu();
//		}
		menu.showMenu();

	}

//	public boolean setIsRunning( boolean onOff ) {
//		isRunning = onOff;
//		return isRunning;
//	}
}
