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
	 * �����Ի���
	 */
	private void dialog() {
		new AlertDialog.Builder(MainActivity.this).setTitle("��ʾ") // ���öԻ������
				.setMessage("ȷ��Ҫ�˳���Ϸ��") // ������ʾ������
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {// ���ȷ����ť
							@Override
							public void onClick(DialogInterface dialog,
									int which) {// ȷ����ť����Ӧ�¼�
								MainActivity.this.finish();
							}
						})

				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {// ��ӷ��ذ�ť
							@Override
							public void onClick(DialogInterface dialog,
									int which) {// ��Ӧ�¼�
							}
						}).show();// �ڰ�����Ӧ�¼�����ʾ�˶Ի���

	}
	// ��ذ���
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
