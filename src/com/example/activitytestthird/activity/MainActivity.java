package com.example.activitytestthird.activity;

import com.example.activitytestthird.R;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tvShowinfo;
	Button btnClick;
	public boolean flag=false;
	
	public Handler hand=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			switch(msg.what){
			case 0x11:
				int randNum=(int)(Math.random()*100)+1;
				tvShowinfo.setText(String.valueOf(randNum));
				break;
			case 0x12:
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvShowinfo=(TextView) this.findViewById(R.id.tvShowinfo);
		btnClick=(Button)this.findViewById(R.id.btnClick);
		
		btnClick.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(flag==false){
					flag=true;
				}else{
					flag=false;
				}
				
				new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							while(true){
								Thread.sleep(30);
								Message msg=new Message();
								if(flag){
									msg.what=0x11;
								}else{
									msg.what=0x12;
								}
								hand.sendMessage(msg);
								
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}).start();

			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
