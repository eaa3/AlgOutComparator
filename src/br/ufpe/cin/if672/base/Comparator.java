package br.ufpe.cin.if672.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Comparator {
	
	private static final int MAX_SIZE = 50;
	
	private  File arq1,arq2;
	private  int matchCount,mismatchCount, totalLines;
	private  int[] mismatchedLines;
	private  int nElem;
	
	public Comparator(String arq1, String arq2)
	{
		this.arq1 = new File(arq1);
		this.arq2 = new File(arq2);
		
		this.matchCount = 0;
		this.mismatchCount = 0;
		this.totalLines = 0;
		this.mismatchedLines = new int[Comparator.MAX_SIZE];
		this.nElem = 0;
	}

	public File getArq1() {
		return arq1;
	}
	
	public void setArq1(String arq1)
	{
		this.arq1 = new File(arq1);
	}
	
	public void setArq2(String arq2)
	{
		this.arq2 = new File(arq2);
	}

	public File getArq2() {
		return arq2;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public int getMismatchCount() {
		return mismatchCount;
	}

	public int getTotalLines() {
		return totalLines;
	}
	
	public float getPercentMatched()
	{
		return 100.0f*this.matchCount/this.totalLines;
	}
	
	public void play()
	{
		this.matchCount = 0;
		this.mismatchCount = 0;
		this.totalLines = 0;
		this.nElem = 0;
		
		try {
			BufferedReader readerArq1 = new BufferedReader(new FileReader(this.arq1));
			BufferedReader readerArq2 = new BufferedReader(new FileReader(this.arq2));
			String lineArq1, lineArq2;
			lineArq1 = lineArq2 = null;
			boolean isEnd = false;
			
			
			
			while( !isEnd )
			{
				isEnd = ((lineArq1 = readerArq1.readLine()) == null);
				isEnd = ((lineArq2 = readerArq2.readLine()) == null && isEnd);
				
				if( lineArq1!=null && lineArq1.equals(lineArq2) ) {
					this.matchCount++;
				}
				else if( !isEnd ){
					
					if(this.mismatchCount<Comparator.MAX_SIZE){
						this.mismatchedLines[this.mismatchCount] = this.totalLines + 1;
						this.nElem++;
					}
					this.mismatchCount++;
				}
				
				if(!isEnd) this.totalLines++;
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int[] getMismatchedLines()
	{
		return this.mismatchedLines;
	}
	
	public String getMismatchedLinesToString()
	{
		String out = new String();
		
		for(int i=0; i < this.nElem; i++)
		{
			out += this.mismatchedLines[i] + " ";
		}
		
		return out;
	}
	
	public int getNElem()
	{
		return this.nElem;
	}
	

}
