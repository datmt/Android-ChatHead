package henrychuang.tw.chatheadmsg;

import henrychuang.tw.chatheadmsg.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
	
	public static Button btnStartService, btnShowMsg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (!Settings.canDrawOverlays(this))
		{
			Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" +this.getPackageName()));
			startActivityForResult(intent, 1111);
		}

		btnStartService = (Button)findViewById(R.id.btnStartService);
		btnShowMsg = (Button)findViewById(R.id.btnMsg);
		
		btnStartService.setOnClickListener(lst_StartService);
		btnShowMsg.setOnClickListener(lst_ShowMsg);
	}
	
	
	Button.OnClickListener lst_StartService = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			startService(new Intent(Main.this, ChatHeadService.class));			
		}
		
	};
	
	
	Button.OnClickListener lst_ShowMsg = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			java.util.Date now = new java.util.Date();
			String str = "test by henry  " + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
			
			Intent it = new Intent(Main.this, ChatHeadService.class);
			it.putExtra(Utility.EXTRA_MSG, str);
			startService(it);
		}
		
	};
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if (requestCode == 1111)
		{
			Toast.makeText(this, "LOK", Toast.LENGTH_SHORT).show();
		}
	}
}
