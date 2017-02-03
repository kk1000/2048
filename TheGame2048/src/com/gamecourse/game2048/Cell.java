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
		numView.setBackgroundColor(Color.argb(10, 229, 0, 0));  //���ñ�����ɫ
		numView.setGravity(Gravity.CENTER);   //�������������ʾ
		numView.setTextColor(android.graphics.Color.parseColor("#363634"));
		numView.setTextSize(32);  //���������С
		FrameLayout.LayoutParams LayoutP=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LayoutP.setMargins(2, 2, 2, 2); //���ñ߾�  
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
