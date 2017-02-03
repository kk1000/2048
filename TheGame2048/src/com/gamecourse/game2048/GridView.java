package com.gamecourse.game2048;


import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
public  class GridView extends GridLayout {
	private Cell[][] cellsArray = new Cell[4][4];
	private int score = 0; //计算得分
	private MediaPlayer player;
	private TextView tv;
	private Button btn;
	
	Handler handler = new Handler(){

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		if(msg.what==1){
			tv.setText("分数\n"+String.valueOf(score));
		}
	}
	
};

	public GridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGridView();

	}

	public GridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGridView();

	}

	public GridView(Context context) {
		super(context);
		initGridView();
	}
	
	
	/**
	 * 在当前GridView分别创建TextView、button对象用来显示分数、按钮，并初始化
	 */
	private void getInitView(){
			LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
			tv = new TextView(getContext());
			tv.setTextSize(30);
			tv.setTextColor(Color.rgb(102, 88, 131));
			tv.setText("分数\n"+String.valueOf(score));
		    addView( tv, textParams );
		    btn = new Button(getContext());
		    btn.setText("重新开始");
		    addView(btn);
		    
		    //为按钮注册监听器，设置游戏重新开始
		    btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					for(int j=0;j<=3;j++)  //每次取一列
					{
						for(int i=3;i>=0;i--) //右-》左
						{
							cellsArray[i][j].setNum(0);
						}
					}
					score = 0; //分数重新初始为0
					addRandomNum();
					addRandomNum();
				}
			});
	}
	
	/**
	 * 设置空格数的背景颜色
	 */
	private void setNumBackGround(){
		
		for(int j=0;j<=3;j++)  //每次取一列
		{
			for(int i=3;i>=0;i--) //右-》左
			{
				int curNum=cellsArray[i][j].getNum();
				String numColor = "#CDC1B4";
				switch(curNum){
				case 0:
					numColor = "#E3D8D3";
					break;
				case 4:
					numColor = "#EDE0C8";
					break;
				case 2:
					numColor = "#EEE4DA";
					break;
				case 8:
					numColor = "#F2B179";
					break;
				case 16:
					numColor = "#F59563";
					break;
				case 32:
					numColor = "#F0583B";
					break;
				case 64:
					numColor = "#EDCF72";
					break;
				case 128:
					break;
				case 256:
					break;
				case 512:
				case 1024:
				case 2048:
				case 4096:
				case 8192:
					numColor ="#00C2E2";
				}
				
				cellsArray[i][j].setBackgroundColor(android.graphics.Color.parseColor(numColor));
			}
		}
		
	}

	/**
	 * 播放音乐的方法
	 */
	private  void playMusic(){
		if(player!=null){
			player.release();
		}
		player = player.create(getContext(), R.drawable.music);
		player.start();
	}
	
	/**
	 * 计分的方法
	 */
	private void addScore(){
//		创建一个定时器
		Timer timer = new Timer(){};
//	        创建一个计时任务
		TimerTask timerTask  = new TimerTask() {
			
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		}; 
		// 启动定时器
		timer.schedule(timerTask,500,500);
	}
	
	/**
	 * 判断游戏是否结束
	 */
/*	private void checkOut(){
		
				for (int i = 0; i <= 3; i++)
					for (int j = 0; j <= 3; j++) {
						int curNum=cellsArray[i][j].getNum();
						if (curNum!= 0) {
						//往前找到第一个非空的cell
						for(int leftNext=j-1;leftNext>=0;leftNext--)
						{
							while(i==3&&j==3&&leftNext==0){
							if(cellsArray[i][leftNext].getNum()!=0){  //找到了一个不是空的
							new AlertDialog.Builder(getContext()).setTitle("提示") // 设置对话框标题
							.setMessage("game over") // 设置显示的内容
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {// 添加确定按钮
										@Override
										public void onClick(DialogInterface dialog,
												int which) {// 确定按钮的响应事件
										}
									})

							.setNegativeButton("取消", new DialogInterface.OnClickListener() {// 添加返回按钮
										@Override
										public void onClick(DialogInterface dialog,
												int which) {// 响应事件
										}
									}).show();// 在按键响应事件中显示此对话框
						}

					}
				}
			}
		}
	}
	*/
	
	/**
	 * 添加随机数的方法
	 */
	private void addRandomNum() {
		
		// 产生一个随机数，2，4，出现的比例2：1
		int randomNum;
		if (Math.random() > 0.3)
			randomNum = 2;
		else
			randomNum = 4;

		// 确定要添加数字的位置，先找到所有的空白单元格，再随机选择一个空白单元格
		int[] blankCellsi = new int[16];
		int[] blankCellsj = new int[16];
		for (int i = 0; i < 16; i++) {
			blankCellsi[i] = 0;
			blankCellsj[i] = 0;
		}

		int blankCellsNum = 0;
		for (int i = 0; i <= 3; i++)
			for (int j = 0; j <= 3; j++) {
				if (cellsArray[i][j].getNum() == 0) {
					blankCellsi[blankCellsNum] = i;
					blankCellsj[blankCellsNum] = j;
					blankCellsNum++;
				}

			}

		int randomLocation = (int) (Math.random() * blankCellsNum);
		cellsArray[blankCellsi[randomLocation]][blankCellsj[randomLocation]]
				.setNum(randomNum);
		// R[0,1)*8->[0,8)->[0,7]
		setNumBackGround();
//		checkOut();
	}
	
	/**
	 * 为GridView界面添加触摸事件的方法
	 */
	private void initGridView() {
		
		//设置触摸事件
		OnTouchListener myListener = new OnTouchListener() {

			private float startX = 0, startY = 0, endX, endY, detaX, detaY;
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				//表示用户开始触摸
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				//表示用户抬起了手指 
				case MotionEvent.ACTION_UP:
					endX = event.getX();
					endY = event.getY();
					detaX = endX - startX;
					detaY = endY - startY;

					if (Math.abs(detaX) >= Math.abs(detaY)) // 水平方向，X轴
					{
						if (detaX > 0) 
						{
							slipRight();
						}

						else
							slipLeft();

					} else // 垂直方向，Y轴
					{
						if (detaY > 0) 
							slipDown();
						else
							slipUp();
					}
					break;

				}
				return true;
			}

		};
		//为GridView注册监听器
		this.setOnTouchListener(myListener);
		
	}

	/**
	 * 初始化空格数的方法
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		int cellLen = (Math.min(w, h) - 5) / 4;
		this.setColumnCount(4);

		Cell tempCell;

		for (int i = 0; i <= 3; i++)
			for (int j = 0; j <= 3; j++) {
				tempCell = new Cell(getContext());
				addView(tempCell, cellLen, cellLen);
				cellsArray[i][j] = tempCell;

			}
		addScore();
		getInitView();
		//添加两次随机数
		addRandomNum();
		addRandomNum();
		
	}
	
	/**
	 * 向右滑动的方法
	 */
	private void slipRight()
	{
		boolean isAction=false;
		
		for(int i=0;i<=3;i++)  //每次取一行
		{
			for(int j=3;j>=0;j--) //右-》左
			{
				int curNum=cellsArray[i][j].getNum();
				
				//往前找到第一个非空的cell
				for(int leftNext=j-1;leftNext>=0;leftNext--)
				{
					
					if(cellsArray[i][leftNext].getNum()!=0){  //找到了一个不是空的
						int leftNextNum=cellsArray[i][leftNext].getNum();
						if(curNum==0){
							//当我为空,就转移非空元素
							cellsArray[i][j].setNum(leftNextNum);
							cellsArray[i][leftNext].setNum(0);
							j++;
							isAction=true;
							
						}else{
							//当前不空,如果相等就合并，如果不等就处理左边一个元素
							if(curNum==leftNextNum)
							{
								//相等就合并
								cellsArray[i][j].setNum(2*curNum);
								score+=2*curNum;
								cellsArray[i][leftNext].setNum(0);
								playMusic();
								isAction=true;
								
							}
								
						}	
						break;						
					}
					
				
				} //for(int leftNext=j-1;leftNext>=0;leftNext--)					
			
			} //for(int j=3;j>=0;j--) //右-》左			
			
		}//for(int i=0;i<=3;i++)  //每次取一行	
		
		if(isAction){
			addRandomNum();
		}
	} //end of slipRight()
	
	/**
	 * 向左滑动的方法
	 */
	private void slipLeft()
	{
		boolean isAction=false;
		for(int i=0;i<=3;i++)  //每次取一行
		{
			for(int j=0;j<4;j++) //右-》左
			{
				int curNum=cellsArray[i][j].getNum();
				
				//往前找到第一个非空的cell
				for(int rightNext=j+1;rightNext<4;rightNext++)
				{
					
					if(cellsArray[i][rightNext].getNum()!=0){  //找到了一个不是空的
						int NextNum=cellsArray[i][rightNext].getNum();
						if(curNum==0){
							//当我为空,就转移非空元素
							cellsArray[i][j].setNum(NextNum);
							cellsArray[i][rightNext].setNum(0);
							j--;
							isAction=true;
							
						}else{
							//当前不空,如果相等就合并，如果不等就处理左边一个元素
							if(curNum==NextNum)
							{
								//相等就合并
								cellsArray[i][j].setNum(2*curNum);
								score+=2*curNum;
								cellsArray[i][rightNext].setNum(0);	
								playMusic();
								isAction=true;
							}							
						}	
						break;						
					}
				
				} //for next		
			} //for(int j=	
		}//for(int i	
		if(isAction)
			addRandomNum();
	} //end of slipLeft()
	
	
	/**
	 * 向上滑动的方法
	 */
	private void slipUp()
	{
		boolean isAction=false;
		for(int j=0;j<=3;j++)  //每次取一列
		{
			for(int i=0;i<4;i++) //右-》左
			{
				int curNum=cellsArray[i][j].getNum();
				
				//往前找到第一个非空的cell
				for(int downNext=i+1;downNext<4;downNext++)
				{
					
					if(cellsArray[downNext][j].getNum()!=0){  //找到了一个不是空的
						int NextNum=cellsArray[downNext][j].getNum();
						if(curNum==0){
							//当我为空,就转移非空元素
							cellsArray[i][j].setNum(NextNum);
							cellsArray[downNext][j].setNum(0);
							i--;
							isAction=true;
							
						}else{
							//当前不空,如果相等就合并，如果不等就处理左边一个元素
							if(curNum==NextNum)
							{
								//相等就合并
								cellsArray[i][j].setNum(2*curNum);
								score+=2*curNum;
								cellsArray[downNext][j].setNum(0);	
								playMusic();
								isAction=true;
							}							
						}	
						break;						
					}
				} //for next		
			} //for(int j=	
		}//for(int i	
		if(isAction)
			addRandomNum();
	} //end of slipUp()
	
	/**
	 * 向下滑动的方法
	 */
	private void slipDown()
	{
		boolean isAction=false;
		for(int j=0;j<=3;j++)  //每次取一列
		{
			for(int i=3;i>=0;i--) //右-》左
			{
				int curNum=cellsArray[i][j].getNum();
				
				//往前找到第一个非空的cell
				for(int upNext=i-1;upNext>=0;upNext--)
				{
					
					if(cellsArray[upNext][j].getNum()!=0){  //找到了一个不是空的
						int NextNum=cellsArray[upNext][j].getNum();
						if(curNum==0){
							//当我为空,就转移非空元素
							cellsArray[i][j].setNum(NextNum);
							cellsArray[upNext][j].setNum(0);
							i++;
							isAction=true;
							
						}else{
							//当前不空,如果相等就合并，如果不等就处理左边一个元素
							if(curNum==NextNum)
							{
								//相等就合并
								cellsArray[i][j].setNum(2*curNum);
								score+=2*curNum;
								cellsArray[upNext][j].setNum(0);	
								playMusic();
								isAction=true;
							}							
						}
						break;						
					}
					
				
				} //for next		
			} //for(int j=	
		}//for(int i	
	if(isAction)
		addRandomNum();
	} //end of slipUp()

	
	

}
