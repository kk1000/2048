package com.gamecourse.game2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Cell extends FrameLayout {
	
	private TextView numView;
	private int num;

	public Cell(Context context) {
		super(context);
		
		numView=new TextView(getContext());
		numView.setBackgroundColor(Color.argb(10, 229, 0, 0));  //设置背景颜色
		numView.setGravity(Gravity.CENTER);   //设置字体居中显示
		numView.setTextColor(android.graphics.Color.parseColor("#363634"));
		numView.setTextSize(32);  //设置字体大小
		FrameLayout.LayoutParams LayoutP=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LayoutP.setMargins(2, 2, 2, 2); //设置边距  
//		LayoutP.setMargins(left, top, right, bottom)
		addView(numView, LayoutP);
		
		setNum(0);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		
		if(num==0)
			numView.setText("");
		else
			numView.setText(num+"");
	}
	
	

	

}
