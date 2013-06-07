package br.ufpe.cin.if672.gui;

import java.awt.EventQueue;

import br.ufpe.cin.if672.base.Comparator;

public class Main {

	
	public static void main(String[] args) {
		
		final AlgoutCompApp algoutComp = new AlgoutCompApp("AlgoutComparator - by Ermano Arruda(eaa3)");
		
		EventQueue.invokeLater( new Runnable() {
			
			@Override
			public void run() {
				
				algoutComp.pack();
	
				algoutComp.setVisible(true);
				
				
			}
		});

	}

}
