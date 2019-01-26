package th.ac.kmutnb.diagnose;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class MyAlert {
    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void normalDialog(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false); // ทำให้กดปุ่มย้อนกลับของโทรศัพท์ไม่ได้ ต้องกดปุ่มที่ข้อความเด้งขึ้นมาเท่านั้น
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { //ปุ่มที่ขึ้นบนข้อความ
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //เมื่อกดปุ่ม OK ป๊อปอัพจะหายไป
            }
        });
        builder.show();

    }

}
