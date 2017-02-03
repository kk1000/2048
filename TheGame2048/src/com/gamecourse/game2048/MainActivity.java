package com.gamecourse.game2048;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;


public  class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	/**
	 * 弹出对话框
	 */
	private void dialog() {
		new AlertDialog.Builder(MainActivity.this).setTitle("提示") // 设置对话框标题
				.setMessage("确定要退出游戏？") // 设置显示的内容
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {// 添加确定按钮
							@Override
							public void onClick(DialogInterface dialog,
									int which) {// 确定按钮的响应事件
								MainActivity.this.finish();
							}
						})

				.setNegativeButton("取消", new DialogInterface.OnClickListener() {// 添加返回按钮
							@Override
							public void onClick(DialogInterface dialog,
									int which) {// 响应事件
							}
						}).show();// 在按键响应事件中显示此对话框

	}
	// 监控按键
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				dialog();
			} else if (keyCode == KeyEvent.KEYCODE_HOME) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);
			}
			return super.onKeyDown(keyCode, event);
		}
}
