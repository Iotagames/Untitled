package iotagames.cybergame.ui;

import java.util.ArrayList;

public class TextField extends Text {
	public String data;
	protected ArrayList<String> dataRows;
	protected String parsed;
	public int cols = 50;
	public int rows = -1;
	
	public TextField(int x, int y) {
		this("",x,y,10,-1);
	}
	
	public TextField(String data, int x, int y,int cols) {
		this(data,x,y,cols,-1);
	}
	
	public TextField(String data, int x, int y,int cols,int rows) {
		super("",x,y);
		dataRows = new ArrayList<String>();
		this.cols = cols;
		this.rows = rows;
		parse(data,cols,rows);
	}
	
	
	public void parse(String data, int cols) {
		parse(data,cols,rows);
	}
	
	public void parse(String data, int cols, int rows) {
		if (data == null) data = "";
		this.data = data;
		dataRows.clear();
		String str;
		for (int j=0;j<data.length();) {
			str="";
			for (;j<data.length()&&font.getWidth(str)<cols;++j) {
				String character = data.substring(j,j+1);
				if (character.equals("\n")) {
					dataRows.add(str);
					str="";
				}
				else str+=character;
			}
			if (str.length()>1 && j<data.length()) {
				if (!str.substring(str.length()-1,str.length()).matches(" ")) {
					int found=str.lastIndexOf(' ');
					int dif=str.length()-found;
					if (found>0&&dif>0) {
						str=str.substring(0,found);
						j-=dif;
					}
				}
			}
			str = str.trim();
			dataRows.add(str);
		}
		buildText(0,rows);
	}
	
	public void buildText(int rowStart, int rows) {
		parsed="";
		for (int i=rowStart;i<dataRows.size();++i) {
			parsed += dataRows.get(i) + "\n";
			if (i>rows&&rows>0) break; 
		}
		string=parsed;
	}
	
	public void setText(String text) {
		super.setText(text);
		parse(text,cols,rows);
	}
	
	public void setSize(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		parse(data,cols,rows);
	}
	
	public String originalData() {
		return data;
	}
}
