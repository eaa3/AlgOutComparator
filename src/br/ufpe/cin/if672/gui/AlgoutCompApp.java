package br.ufpe.cin.if672.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.editor.ChartEditorFactory;
import org.jfree.data.general.DefaultPieDataset;

import br.ufpe.cin.if672.base.Comparator;

public class AlgoutCompApp extends JFrame implements ActionListener{

	private JTextField jTfArq1,jTfArq2;
	private JLabel lbArq1,lbArq2;
	private JButton jbCompare;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private Comparator comp;
	private JTextArea jTInfo;


	public AlgoutCompApp(String appTitle)
	{
		super(appTitle);

		this.setPreferredSize( new Dimension(640,480) );
		this.setLayout( new FlowLayout(FlowLayout.LEFT) );


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.jTfArq1 = new JTextField();
		this.jTfArq2 = new JTextField();

		this.lbArq1 = new JLabel();
		this.lbArq2 = new JLabel();

		this.jTInfo = new JTextArea();


		this.lbArq1.setText("Arquivo 1");
		this.lbArq2.setText("Arquivo 2");

		this.add(lbArq1);
		this.add(jTfArq1);

		this.add(lbArq2);
		this.add(jTfArq2);

		this.jTfArq1.setPreferredSize(new Dimension(100,30));

		this.jTfArq2.setPreferredSize(new Dimension(100,30));



		this.jbCompare = new JButton("Compare!");



		this.jbCompare.addActionListener(this);

		this.add(this.jbCompare);



		this.chartPanel = new ChartPanel(null);
		this.chartPanel.setPreferredSize(new Dimension(400, 250));
		this.add(this.chartPanel);


		this.jTInfo.setPreferredSize( new Dimension(210, 300));
		this.jTInfo.setBorder(BorderFactory.createTitledBorder("Info"));
		this.jTInfo.setEditable(false);
		this.add(this.jTInfo);


	}

	public DefaultPieDataset createDataset()
	{
		DefaultPieDataset dataset = new DefaultPieDataset();

		this.comp = new Comparator( this.jTfArq1.getText(), this.jTfArq2.getText());

		this.comp.play();

		dataset.setValue("Mismatched Lines", comp.getMismatchCount());
		dataset.setValue("Matched Lines", comp.getMatchCount());





		return dataset;
	}

	public JFreeChart createChart()
	{
		return ChartFactory.createPieChart("Comparation", this.createDataset(), true, true, false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.chart = this.createChart();


		this.chartPanel.setChart(chart);

		this.jTInfo.setText("Total lines: " + this.comp.getTotalLines() +
				"\nMatched lines:"  + this.comp.getMatchCount() +
				"\nMismatched lines: " + this.comp.getMismatchCount() + 
				"\nMatch percent: " + this.comp.getPercentMatched() + 
				"\n\nFirst mismatched lines:" + this.printMismatched());




	}

	private String printMismatched()
	{
		String out = new String();
		int[] mismatchedLines = this.comp.getMismatchedLines();

		for(int i=0; i < this.comp.getNElem(); i++)
		{
			if( i%10 == 0) out += "\n";
			out += mismatchedLines[i] + " ";
		}

		return out;
	}


}
