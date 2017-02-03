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
	private int score = 0; //����÷�
	private MediaPlayer player;
	private TextView tv;
	private Button btn;
	
	Handler handler = new Handler(){

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		if(msg.what==1){
			tv.setText("����\n"+String.valueOf(score));
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
	 * �ڵ�ǰGridView�ֱ𴴽�TextView��button����������ʾ��������ť������ʼ��
	 */
	private void getInitView(){
			LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
			tv = new TextView(getContext());
			tv.setTextSize(30);
			tv.setTextColor(Color.rgb(102, 88, 131));
			tv.setText("����\n"+String.valueOf(score));
		    addView( tv, textParams );
		    btn = new Button(getContext());
		    btn.setText("���¿�ʼ");
		    addView(btn);
		    
		    //Ϊ��ťע���������������Ϸ���¿�ʼ
		    btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					for(int j=0;j<=3;j++)  //ÿ��ȡһ��
					{
						for(int i=3;i>=0;i--) //��-����
						{
							cellsArray[i][j].setNum(0);
						}
					}
					score = 0; //�������³�ʼΪ0
					addRandomNum();
					addRandomNum();
				}
			});
	}
	
	/**
	 * ���ÿո����ı�����ɫ
	 */
	private void setNumBackGround(){
		
		for(int j=0;j<=3;j++)  //ÿ��ȡһ��
		{
			for(int i=3;i>=0;i--) //��-����
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
	 * �������ֵķ���
	 */
	private  void playMusic(){
		if(player!=null){
			player.release();
		}
		player = player.create(getContext(), R.drawable.music);
		player.start();
	}
	
	/**
	 * �Ʒֵķ���
	 */
	private void addScore(){
//		����һ����ʱ��
		Timer timer = new Timer(){};
//	        ����һ����ʱ����
		TimerTask timerTask  = new TimerTask() {
			
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 1;
				handler.sendMessage(msg);
			}
		}; 
		// ������ʱ��
		timer.schedule(timerTask,500,500);
	}
	
	/**
	 * �ж���Ϸ�Ƿ����
	 */
/*	private void checkOut(){
		
				for (int i = 0; i <= 3; i++)
					for (int j = 0; j <= 3; j++) {
						int curNum=cellsArray[i][j].getNum();
						if (curNum!= 0) {
						//��ǰ�ҵ���һ���ǿյ�cell
						for(int leftNext=j-1;leftNext>=0;leftNext--)
						{
							while(i==3&&j==3&&leftNext==0){
							if(cellsArray[i][leftNext].getNum()!=0){  //�ҵ���һ�����ǿյ�
							new AlertDialog.Builder(getContext()).setTitle("��ʾ") // ���öԻ������
							.setMessage("game over") // ������ʾ������
							.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {// ���ȷ����ť
										@Override
										public void onClick(DialogInterface dialog,
												int which) {// ȷ����ť����Ӧ�¼�
										}
									})

							.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {// ��ӷ��ذ�ť
										@Override
										public void onClick(DialogInterface dialog,
												int which) {// ��Ӧ�¼�
										}
									}).show();// �ڰ�����Ӧ�¼�����ʾ�˶Ի���
						}

					}
				}
			}
		}
	}
	*/
	
	/**
	 * ���������ķ���
	 */
	private void addRandomNum() {
		
		// ����һ���������2��4�����ֵı���2��1
		int randomNum;
		if (Math.random() > 0.3)
			randomNum = 2;
		else
			randomNum = 4;

		// ȷ��Ҫ������ֵ�λ�ã����ҵ����еĿհ׵�Ԫ�������ѡ��һ���հ׵�Ԫ��
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
	 * ΪGridView������Ӵ����¼��ķ���
	 */
	private void initGridView() {
		
		//���ô����¼�
		OnTouchListener myListener = new OnTouchListener() {

			private float startX = 0, startY = 0, endX, endY, detaX, detaY;
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				//��ʾ�û���ʼ����
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				//��ʾ�û�̧������ָ 
				case MotionEvent.ACTION_UP:
					endX = event.getX();
					endY = event.getY();
					detaX = endX - startX;
					detaY = endY - startY;

					if (Math.abs(detaX) >= Math.abs(detaY)) // ˮƽ����X��
					{
						if (detaX > 0) 
						{
							slipRight();
						}

						else
							slipLeft();

					} else // ��ֱ����Y��
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
		//ΪGridViewע�������
		this.setOnTouchListener(myListener);
		
	}

	/**
	 * ��ʼ���ո����ķ���
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
		//������������
		addRandomNum();
		addRandomNum();
		
	}
	
	/**
	 * ���һ����ķ���
	 */
	private void slipRight()
	{
		boolean isAction=false;
		
		for(int i=0;i<=3;i++)  //ÿ��ȡһ��
		{
			for(int j=3;j>=0;j--) //��-����
			{
				int curNum=cellsArray[i][j].getNum();
				
				//��ǰ�ҵ���һ���ǿյ�cell
				for(int leftNext=j-1;leftNext>=0;leftNext--)
				{
					
					if(cellsArray[i][leftNext].getNum()!=0){  //�ҵ���һ�����ǿյ�
						int leftNextNum=cellsArray[i][leftNext].getNum();
						if(curNum==0){
							//����Ϊ��,��ת�Ʒǿ�Ԫ��
							cellsArray[i][j].setNum(leftNextNum);
							cellsArray[i][leftNext].setNum(0);
							j++;
							isAction=true;
							
						}else{
							//��ǰ����,�����Ⱦͺϲ���������Ⱦʹ������һ��Ԫ��
							if(curNum==leftNextNum)
							{
								//��Ⱦͺϲ�
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
			
			} //for(int j=3;j>=0;j--) //��-����			
			
		}//for(int i=0;i<=3;i++)  //ÿ��ȡһ��	
		
		if(isAction){
			addRandomNum();
		}
	} //end of slipRight()
	
	/**
	 * ���󻬶��ķ���
	 */
	private void slipLeft()
	{
		boolean isAction=false;
		for(int i=0;i<=3;i++)  //ÿ��ȡһ��
		{
			for(int j=0;j<4;j++) //��-����
			{
				int curNum=cellsArray[i][j].getNum();
				
				//��ǰ�ҵ���һ���ǿյ�cell
				for(int rightNext=j+1;rightNext<4;rightNext++)
				{
					
					if(cellsArray[i][rightNext].getNum()!=0){  //�ҵ���һ�����ǿյ�
						int NextNum=cellsArray[i][rightNext].getNum();
						if(curNum==0){
							//����Ϊ��,��ת�Ʒǿ�Ԫ��
							cellsArray[i][j].setNum(NextNum);
							cellsArray[i][rightNext].setNum(0);
							j--;
							isAction=true;
							
						}else{
							//��ǰ����,�����Ⱦͺϲ���������Ⱦʹ������һ��Ԫ��
							if(curNum==NextNum)
							{
								//��Ⱦͺϲ�
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
	 * ���ϻ����ķ���
	 */
	private void slipUp()
	{
		boolean isAction=false;
		for(int j=0;j<=3;j++)  //ÿ��ȡһ��
		{
			for(int i=0;i<4;i++) //��-����
			{
				int curNum=cellsArray[i][j].getNum();
				
				//��ǰ�ҵ���һ���ǿյ�cell
				for(int downNext=i+1;downNext<4;downNext++)
				{
					
					if(cellsArray[downNext][j].getNum()!=0){  //�ҵ���һ�����ǿյ�
						int NextNum=cellsArray[downNext][j].getNum();
						if(curNum==0){
							//����Ϊ��,��ת�Ʒǿ�Ԫ��
							cellsArray[i][j].setNum(NextNum);
							cellsArray[downNext][j].setNum(0);
							i--;
							isAction=true;
							
						}else{
							//��ǰ����,�����Ⱦͺϲ���������Ⱦʹ������һ��Ԫ��
							if(curNum==NextNum)
							{
								//��Ⱦͺϲ�
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
	 * ���»����ķ���
	 */
	private void slipDown()
	{
		boolean isAction=false;
		for(int j=0;j<=3;j++)  //ÿ��ȡһ��
		{
			for(int i=3;i>=0;i--) //��-����
			{
				int curNum=cellsArray[i][j].getNum();
				
				//��ǰ�ҵ���һ���ǿյ�cell
				for(int upNext=i-1;upNext>=0;upNext--)
				{
					
					if(cellsArray[upNext][j].getNum()!=0){  //�ҵ���һ�����ǿյ�
						int NextNum=cellsArray[upNext][j].getNum();
						if(curNum==0){
							//����Ϊ��,��ת�Ʒǿ�Ԫ��
							cellsArray[i][j].setNum(NextNum);
							cellsArray[upNext][j].setNum(0);
							i++;
							isAction=true;
							
						}else{
							//��ǰ����,�����Ⱦͺϲ���������Ⱦʹ������һ��Ԫ��
							if(curNum==NextNum)
							{
								//��Ⱦͺϲ�
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
